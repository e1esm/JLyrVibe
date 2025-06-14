package com.esm.storage.components;

import com.esm.storage.dto.GeneratedFile;
import com.esm.storage.exceptions.PartitionerException;
import com.esm.storage.models.FileType;
import com.esm.storage.util.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AudioPartitioner implements Partitioner {

    private static final Logger log = LoggerFactory.getLogger(AudioPartitioner.class);

    @Override
    public List<GeneratedFile> FileInChunks(InputStream stream, String filename) throws PartitionerException {
        try {
            Path dirPath = Files.createTempDirectory(FileSystem.filenameWithoutExtension(filename));
            var path = Files.createTempFile(dirPath,
                    FileSystem.filenameWithoutExtension(filename),
                    FileSystem.filenameExtension(filename));
            Files.write(path, stream.readAllBytes());

            this.runFFMEGCommand(path);
            Files.deleteIfExists(path);

            return this.getGeneratedAudioFiles(dirPath);
        }catch (Exception e){
            throw new PartitionerException(e.getMessage(), e);
        }
    }

    @Override
    public FileType getSupportedFileType(){
        return FileType.AUDIO_TYPE;
    }

    private void runFFMEGCommand(Path path) throws Exception {
            Path dir = path.getParent();
            ProcessBuilder processBuilder = new ProcessBuilder(
                    FileSystem.SH_BINARY,
                    "-c",
                    String.format("%s -i %s -hls_time %d -hls_list_size 0 -f hls %s",
                            FileSystem.FFMPEG_BINARY,
                            path,
                            10,
                            Path.of(dir.toString(),
                                    String.format("%s.%s",
                                            FileSystem.filenameWithoutExtension(path),
                                            FileSystem.PLAYLIST_EXTENSION))
                    )
            );

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new PartitionerException("FFmpeg process failed with exit code " + exitCode);
            }
    }

    private List<GeneratedFile> getGeneratedAudioFiles(Path path) throws Exception {
        File[] files = path.toFile().listFiles();
        if (files == null) {
            throw new PartitionerException("Directory not found or inaccessible: " + path);
        }

        List<GeneratedFile> generatedAudioFiles = new ArrayList<>();

        for (File file : files) {
            FileInputStream fileInputStream = new FileInputStream(file);
            generatedAudioFiles.add(new GeneratedFile(fileInputStream, file.getName()));

            log.debug(String.format("Fetched audio file: %s", file.getAbsolutePath()));
        }

        return generatedAudioFiles;
    }
}

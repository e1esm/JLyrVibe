package com.esm.storage.components;

import com.esm.storage.models.FileType;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class AudioPartitioner implements Partitioner {
    @Override
    public List<InputStream> FileInChunks(InputStream stream, String filename) throws IOException {
        var path = Path.of(URI.create(filename));

        path = Files.createTempFile(path, null, null);

        try(BufferedReader bufferedReader = Files.newBufferedReader(path)){

        }finally {
            Files.deleteIfExists(path);
        }


        return null;
    }

    @Override
    public FileType getSupportedFileType(){
        return FileType.AUDIO_TYPE;
    }
}

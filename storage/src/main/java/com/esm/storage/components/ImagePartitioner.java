package com.esm.storage.components;

import com.esm.storage.dto.GeneratedFile;
import com.esm.storage.models.FileType;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class ImagePartitioner implements Partitioner {
    @Override
    public List<GeneratedFile> FileInChunks(InputStream stream, String filename){
        return List.of(new GeneratedFile(stream, filename));
    }

    @Override
    public FileType getSupportedFileType(){
        return FileType.IMAGE_TYPE;
    }
}

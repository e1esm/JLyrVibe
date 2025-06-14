package com.esm.storage.services;

import com.esm.storage.components.Partitioner;
import com.esm.storage.dto.CreatedFileResponse;
import com.esm.storage.models.FileType;
import com.esm.storage.repositories.FileRepository;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    private FileRepository fileRepository;
    private MinioFileService minioFileService;

    @Autowired
    public FileService(@Autowired FileRepository fileRepository, @Autowired MinioFileService minioFileService){
        this.fileRepository = fileRepository;
        this.minioFileService = minioFileService;
    }

    public List<CreatedFileResponse> saveFile(InputStream content, FileType type, String filename, UUID artistID){
        this.minioFileService.saveFile(content, type, filename, artistID);
        return null;
    }


}

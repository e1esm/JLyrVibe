package com.esm.storage.services;

import com.esm.storage.components.PartitionFactory;
import com.esm.storage.exceptions.MinioClientException;
import com.esm.storage.models.FileType;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MinioFileService {
    private PartitionFactory partitionFactory;
    private MinioClient minioClient;

    @Autowired
    public MinioFileService(PartitionFactory partitionFactory, MinioClient minioClient) {
        this.partitionFactory = partitionFactory;
        this.minioClient = minioClient;
    }

    public List<String> saveFile(InputStream file, FileType fileType, String filename, UUID artistId){
        List<String> savedFiles = new ArrayList<>();

        try {
            createBucketIfNotExist(artistId);

            var chunks = this.partitionFactory.
                    getPartitioner(fileType).
                    FileInChunks(file, filename);

                for (var chunk : chunks) {
                    this.minioClient.putObject(PutObjectArgs.
                            builder().
                            bucket(artistId.toString().toLowerCase()).
                            object(filename).
                            stream(chunk, -1, 10485760).
                            build());

                    savedFiles.add(String.format("%s/%s", artistId, filename));
                }
        }catch (Exception e){
            throw new MinioClientException(e.getMessage(), e);
        }

        return savedFiles;
    }

    private void createBucketIfNotExist(UUID artistID) {
        try {
            if (this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(artistID.toString().toLowerCase()).build())) {
                return;
            }

            this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(artistID.toString().toLowerCase()).build());

        }catch (Exception e) {
            throw new MinioClientException("Failed to create bucket" + e.getMessage(), e);
        }

    }


}

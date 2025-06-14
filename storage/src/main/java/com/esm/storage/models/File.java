package com.esm.storage.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "files")
@Data
public class File {

    @Id
    public UUID id;

    @Column(name = "s3_path")
    String s3Path;

    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    FileType fileType;

    Integer size;

    @Column(name = "created_at")
    Instant CreatedAt;

    @Column(name = "deleted_at")
    Instant DeletedAt;

}

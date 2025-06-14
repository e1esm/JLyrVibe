package com.esm.storage.components;

import com.esm.storage.dto.GeneratedFile;
import com.esm.storage.exceptions.PartitionerException;
import com.esm.storage.models.FileType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface Partitioner {
    List<GeneratedFile> FileInChunks(InputStream stream, String filename) throws PartitionerException;
    FileType getSupportedFileType();
}

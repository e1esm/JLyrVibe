package com.esm.storage.components;


import com.esm.storage.models.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class PartitionFactory {
    private final Map<FileType, Partitioner> partitionerMap = new EnumMap<>(FileType.class);

    @Autowired
    public PartitionFactory(List<Partitioner> practitioners) {
        for (Partitioner partitioner : practitioners) {
            this.partitionerMap.put(partitioner.getSupportedFileType(), partitioner);
        }
    }

    public Partitioner getPartitioner(FileType fileType) {
        return partitionerMap.get(fileType);
    }

}

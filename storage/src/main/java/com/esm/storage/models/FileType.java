package com.esm.storage.models;

public enum FileType {
    IMAGE_TYPE("IMAGE_TYPE"),
    AUDIO_TYPE("AUDIO_TYPE");

    private final String type;

    FileType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}

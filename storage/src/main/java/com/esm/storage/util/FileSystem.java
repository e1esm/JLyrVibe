package com.esm.storage.util;

import java.nio.file.Path;

public class FileSystem {

    public static String SH_BINARY = "sh";
    public static String FFMPEG_BINARY = "ffmpeg";
    public static String PLAYLIST_EXTENSION = "m3u8";


    public static String filenameWithoutExtension(Path path){
        String fileName = path.getFileName().toString();

        int dotIndex = getDotIndex(fileName);
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

    public static String filenameWithoutExtension(String path){

        int dotIndex = getDotIndex(path);
        return (dotIndex == -1) ? path : path.substring(0, dotIndex);
    }

    public static String filenameExtension(String fileName){
        int dotIndex = getDotIndex(fileName);

        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

    private static int getDotIndex(String filename){
        return filename.lastIndexOf('.');
    }
}

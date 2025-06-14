package com.esm.storage.controllers;

import com.esm.storage.models.FileType;
import com.esm.storage.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class FileController {

    private FileService fileService;
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    public FileController(@Autowired FileService fileService){
        this.fileService = fileService;
    }

    @PostMapping("/file")

    public ResponseEntity<?> uploadFile(
            @RequestPart(name = "file") MultipartFile file,
            @RequestParam(name = "file_type") String fileType,
    @RequestParam(name = "artist_id") UUID artistID) throws IOException {
        log.info(String.format("Uploading file: %s of type: %s", file.getOriginalFilename(), fileType));

        this.fileService.saveFile(file.getInputStream(), FileType.valueOf(fileType), file.getOriginalFilename(), artistID);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/file")
    public ResponseEntity<?> files() {
        return ResponseEntity.ok(new String[]{
           "Без Ключа.mp3", "Эми.mp3"
        });
    }
}

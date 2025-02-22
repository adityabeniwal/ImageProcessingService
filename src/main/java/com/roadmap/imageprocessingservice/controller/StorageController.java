package com.roadmap.imageprocessingservice.controller;

import com.roadmap.imageprocessingservice.service.AwsStorageService;
import com.roadmap.imageprocessingservice.service.ImageProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class StorageController {


    private AwsStorageService service;
    private ImageProcessingService imageProcessingService;



    @Autowired
    public StorageController(AwsStorageService service,ImageProcessingService imageProcessingService) {
        this.imageProcessingService=imageProcessingService;
        this.service = service;
    }





    //working code below for aws

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(service.uploadFile(file,"test"), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws Exception {
        byte[] data = service.downloadFile(fileName);
        data = imageProcessingService.transformImage(data,250,250);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
}
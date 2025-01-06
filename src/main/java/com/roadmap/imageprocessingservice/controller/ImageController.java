package com.roadmap.imageprocessingservice.controller;



import com.roadmap.imageprocessingservice.dto.UploadImageResponseDTO;
import com.roadmap.imageprocessingservice.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController
{

    ImageStorageService imageStorageService;

    @Autowired
    public ImageController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @PostMapping("")
    ResponseEntity<UploadImageResponseDTO> uplaodImage (@RequestBody MultipartFile file)
    {
        return new ResponseEntity<>(imageStorageService.saveImage(file), HttpStatus.OK);

    }
}

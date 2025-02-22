package com.roadmap.imageprocessingservice.controller;



import com.roadmap.imageprocessingservice.dto.RetrieveImageResponseDto;
import com.roadmap.imageprocessingservice.dto.UploadImageResponseDTO;
import com.roadmap.imageprocessingservice.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{imageId}")
    ResponseEntity<ByteArrayResource> retrieveImage (@PathVariable int imageId)
    {
        RetrieveImageResponseDto retrieveImageResponseDto = imageStorageService.retrieveImage(imageId);
        byte[] data = retrieveImageResponseDto.data;
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + retrieveImageResponseDto.fileName + "\"")
                .body(resource);
    }
}

package com.roadmap.imageprocessingservice.service;


import com.roadmap.imageprocessingservice.dto.UploadImageResponseDTO;
import com.roadmap.imageprocessingservice.model.ImageEntity;
import com.roadmap.imageprocessingservice.model.ImageRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageStorageService
{
    ImageRepo imageRepo;
    AwsStorageService awsStorageService;

    ModelMapper modelMapper;

    @Autowired
    public ImageStorageService(ImageRepo imageRepo,AwsStorageService awsStorageService,ModelMapper modelMapper)
    {
        this.imageRepo=imageRepo;
        this.awsStorageService=awsStorageService;
        this.modelMapper=modelMapper;
    }

    @Transactional
    public UploadImageResponseDTO saveImage (MultipartFile file)
    {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(System.currentTimeMillis() + "_" + file.getOriginalFilename());
        imageEntity.setFileSize(file.getSize());
        imageEntity.setType(file.getContentType());

        imageEntity = imageRepo.save(imageEntity);
        awsStorageService.uploadFile(file,imageEntity.getFileName());

        return modelMapper.map(imageEntity, UploadImageResponseDTO.class);
    }
}

package com.roadmap.imageprocessingservice.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RetrieveImageResponseDto
{
    public byte[] data;
    public String fileName;
}

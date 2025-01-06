package com.roadmap.imageprocessingservice.service;


import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageProcessingService
{

    private BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] createBytesFromImage(BufferedImage img)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes;
        try{
            ImageIO.write(img, "jpg", baos);
            bytes = baos.toByteArray();
        } catch(IOException e)
        {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    public byte[] transformImage (byte[] file,int targetWidth,int targetHeight) throws Exception {
        BufferedImage convertedImg = this.resizeImage(this.createImageFromBytes(file),targetWidth,targetHeight);

        return this.createBytesFromImage(convertedImg);
    }




    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws Exception {
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
    }
}

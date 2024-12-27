package com.roadmap.imageprocessingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class ImageProcessingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageProcessingServiceApplication.class, args);
    }

}

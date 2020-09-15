package com.refinitiv.nlpdemo;

import com.refinitv.nlpdemo.controller.NlpController;
import com.refinitv.nlpdemo.service.ImageProcessingService;
import com.refinitv.nlpdemo.service.Pipeline;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {NlpController.class, Pipeline.class, ImageProcessingService.class})
public class NlpdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NlpdemoApplication.class, args);
    }

}

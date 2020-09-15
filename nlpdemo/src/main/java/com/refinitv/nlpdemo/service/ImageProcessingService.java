package com.refinitv.nlpdemo.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ImageProcessingService {

    public String processImage() {
        String text = null;
        ITesseract tesseract = new Tesseract();
        try {

            tesseract.setDatapath("/home/akshat/Documents/nlpdemo/src/main/resources/tessdata");

            // the path of your tess data folder
            // inside the extracted file

            text = tesseract.doOCR(new File("/home/akshat/Documents/nlpdemo/src/main/resources/docs/case.PDF"));

            // path of your image file
            System.out.print(">>>>>>>>>>>>");
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return text;

    }
}

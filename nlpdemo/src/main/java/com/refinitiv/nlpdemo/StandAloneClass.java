package com.refinitiv.nlpdemo;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StandAloneClass {

    public static void main(String[] args) throws IOException {
        ITesseract tesseract = new Tesseract();
        try {

            tesseract.setDatapath("/home/akshat/Documents/nlpdemo/src/main/resources/tessdata");

            // the path of your tess data folder
            // inside the extracted file
            String text
                    = tesseract.doOCR(new File("/home/akshat/Documents/nlpdemo/src/main/resources/docs/tabular.png"));
            List<String> allMatches = new ArrayList<>();
            Pattern pattern = Pattern.compile("((\\w+\\s+)+[|](\\s*\\w+\\s+)+[|](\\s*\\w+\\s*)+)");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                allMatches.add(matcher.group(0));
            }
            // path of your image file
            System.out.print(">> >>>>>>>>>>");
            System.out.println(text);

            System.out.println("Matching Pattern ");
            if (allMatches.size() > 0) {
                allMatches.stream().forEach(s -> System.out.println(s));
            } else {
                System.out.println("No Match");
            }


        } catch (TesseractException e) {
            e.printStackTrace();
        }


    }


}

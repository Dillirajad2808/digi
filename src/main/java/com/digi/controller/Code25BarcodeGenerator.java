package com.digi.controller;

import java.io.File;
import java.io.IOException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class Code25BarcodeGenerator {

    public static void main(String[] args) {
        // Data to encode in the barcode
    	String data = "12345"; // Change this to your desired data

        // Set encoding hints for Code 25 format
        MultiFormatWriter writer = new MultiFormatWriter();
        BarcodeFormat format = BarcodeFormat.ITF; // Code 25 format
        int width = 300; // Width of the barcode
        int height = 100; // Height of the barcode
        EncodeHintType hintType = EncodeHintType.MARGIN;
        int margin = 2;

        try {
            BitMatrix bitMatrix = writer.encode(data, format, width, height);
            File outputFile = new File("D:\\code25_barcode.png");


            // Save the barcode as an image
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", outputFile.toPath());
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}



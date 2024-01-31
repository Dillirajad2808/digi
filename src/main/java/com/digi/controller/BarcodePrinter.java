package com.digi.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

 

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

 

public class BarcodePrinter {

 

    public static void main(String[] args) throws WriterException {
        // Barcode content you want to print
        String barcodeContent = "123456789";

 

        // Define the barcode format (e.g., QR Code, CODE_128, etc.)
        BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;

 

        // Set the path where you want to save the generated barcode image
        String filePath = "barcode.png";

 

        try {
            // Generate the barcode as a BitMatrix
            BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeContent, barcodeFormat, 200, 100);

 

            // Save the barcode image to a file
            File barcodeFile = new File("D:\\Reports\\test.png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", barcodeFile.toPath());

 

            System.out.println("Barcode printed and saved as " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.digi.controller;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class sd {
    public static void main(String[] args) {
        String barcodeValue = "123456789";  // Replace with your barcode value
        String textValue = "2577";

        try {
            // Generate the barcode using Barcode4J
            Code128Bean bean = new Code128Bean();
            final int dpi = 300;
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    new FileOutputStream("barcode.png"), "image/png", dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            bean.generateBarcode(canvas, barcodeValue);
            canvas.finish();

            // Open the barcode image
            BufferedImage image = ImageIO.read(new File("barcode.png"));
            Graphics2D g2d = image.createGraphics();
            
            // Add a white background for the text
            g2d.setColor(Color.WHITE);
            g2d.fillRect(20, 30, 200, 30); // Adjust the dimensions as needed
            
            // Add the text with a black color
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 14));
            g2d.drawString(textValue, 15, 25);

            // Save the image with barcode and text
            ImageIO.write(image, "png", new File("D:\\Reports\\barcode_label.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

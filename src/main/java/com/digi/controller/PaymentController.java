package com.digi.controller;

import com.google.zxing.BarcodeFormat;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PaymentController {

 
    private String stripePublicKey = "pk_test_51NrwGhSEfhvkZqGMiwlTwvdrMEKtyWP1EE6q36ZaR46iSAlKFcYrENCMWhX1vZbHapTKuYkQjZC8B9tHlOowXat800Es4WFTYl";
    

    
    private String stripeSecretKey= "sk_test_51NrwGhSEfhvkZqGMQHMFkvikPFlXsaUKk7y7eZUISL9R7XmJR5HTr7WjRkIJxJGUl97WYdChAk34Dyfxh3kzgXFK004aDbR71q";

    @GetMapping("/payment")
    public String showPaymentForm(Model model) {
        model.addAttribute("stripePublicKey", stripePublicKey);
        
        return "Viewable";
    }
    @PostMapping("/allison")
    public ResponseEntity<String> handleFormSubmission(@RequestBody Map<String, String> formData) {
        // Process the form data as needed
        System.out.println("Received form data:");
        formData.forEach((key, value) -> System.out.println(key + ": " + value));

        // You can perform additional logic here

        // Respond with a success message
        return new ResponseEntity<>("Data received successfully!", HttpStatus.OK);
    }
    
    
    @PostMapping("/charge")
    public ModelAndView processPayment(@RequestParam("amount") Integer amount) throws StripeException, UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView("payment_success");
        modelAndView.addObject("amount", amount);

        // Set up Stripe API key
        Stripe.apiKey = stripeSecretKey;

        // Create a PaymentIntent with the payment amount
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount * 100); // Amount in cents
        params.put("currency", "inr");

        PaymentIntent intent = PaymentIntent.create(params);

        // Create a Deep Link URL for PhonePe
        String upiId = "7397312808@ybl";
        String merchantName = "Merchant";
        String transactionNote = "Payment for Order";
        String deepLinkURL = generatePhonePeDeepLink(upiId, merchantName, transactionNote, amount);

        modelAndView.addObject("deepLinkURL", deepLinkURL);

        // Generate QR code from the deep link
        String qrCodeBase64 = generateQRCode(deepLinkURL);
        modelAndView.addObject("qrCodeBase64", qrCodeBase64);

        return modelAndView;
    }

    private String generatePhonePeDeepLink(String upiId, String merchantName, String transactionNote, Integer amount) throws UnsupportedEncodingException {
        String encodedTransactionNote = URLEncoder.encode(transactionNote, "UTF-8");
        String deepLinkURL = "upi://pay?pa=" + upiId +
                            "&pn=" + URLEncoder.encode(merchantName, "UTF-8") +
                            "&tn=" + encodedTransactionNote +
                            "&am=" + amount +
                            "&cu=INR";
        return deepLinkURL;
    }

    private String generatePhonePeDeepLinkTest(String upiId, String merchantName, String transactionNote, Integer amount) throws UnsupportedEncodingException {
        String encodedTransactionNote = URLEncoder.encode(transactionNote, "UTF-8");
        String deepLinkURL = "upi://pay?pa=" + upiId +
                            "&pn=" + URLEncoder.encode(merchantName, "UTF-8") +
                            "&tn=" + encodedTransactionNote +
                            "&am=" + amount +
                            "&cu=INR";
        return deepLinkURL;
    }


    private String generateQRCode(String text) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            byte[] qrCodeBytes = outputStream.toByteArray();
            return java.util.Base64.getEncoder().encodeToString(qrCodeBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

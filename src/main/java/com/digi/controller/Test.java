package com.digi.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Test {
    public static void main(String[] args) {
        // Set up ChromeDriver and ChromeOptions
        System.setProperty("webdriver.chrome.driver", "D:\\New folder\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        // Navigate to the website
        driver.get("https://www.paypal.com/in/signin");

        // Find and fill in the email field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("sindhu");

     

        // Use WebDriverWait to wait for the "Continue" button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("actionContinue")));
        continueButton.click();

        // Close the browser
        driver.quit();
    }
}

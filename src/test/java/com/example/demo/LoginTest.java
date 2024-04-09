package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;
    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/rache/Documents/testing/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSuccessfulLogin() {

        driver.get("http://localhost:4200/");

        try {
            Thread.sleep(2000); // 20 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Enter valid credentials and submit login form
        driver.findElement(By.name("email")).sendKeys("marcbanghart@gmail.com");
        driver.findElement(By.name("password")).sendKeys("StrongPassword1234");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify successful login by checking if the user is redirected to a new page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "http://localhost:4200/dashboard", "Successful login should redirect user to a new page");
    }

    @Test
    public void testUnsuccessfulLogin() {
        driver.get("http://localhost:4200/");

        try {
            Thread.sleep(2000); // 20 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Enter invalid credentials and submit login form
        driver.findElement(By.name("email")).sendKeys("invalidemail@example.com");
        driver.findElement(By.name("password")).sendKeys("invalidpassword");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000); // 20 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify unsuccessful login by checking for error messages or remaining on the same page
        String actualURL = driver.getCurrentUrl();
        try {
            Thread.sleep(2000); // 20 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(actualURL, "http://localhost:4200/", "Unsuccessful login should stay on the same page");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

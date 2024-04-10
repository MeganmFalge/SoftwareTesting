package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AdministrationPageTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/rache/Documents/testing/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://localhost:4200/");

        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Enter valid credentials and submit login form
        driver.findElement(By.name("email")).sendKeys("marcbanghart@gmail.com");
        driver.findElement(By.name("password")).sendKeys("StrongPassword1234");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//p[contains(text(),'Admin')]")).click();

        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAddAndDeleteUser() {
        // Click on the "New User" button
        WebElement newUserButton = driver.findElement(By.cssSelector("button.newuser"));
        newUserButton.click();

        // Wait for the "Create New User" section to appear
        WebElement createNewUserSection = driver.findElement(By.id("createNewUser"));
        Assert.assertTrue(createNewUserSection.isDisplayed(), "Create New User section should be displayed");

        // Fill in user details
        WebElement firstNameInput = driver.findElement(By.cssSelector("input[placeholder='First Name']"));
        WebElement lastNameInput = driver.findElement(By.cssSelector("input[placeholder='Last Name']"));
        WebElement emailInput = driver.findElement(By.cssSelector("input[placeholder='Email']"));
        firstNameInput.sendKeys("John");
        lastNameInput.sendKeys("Doe");
        emailInput.sendKeys("john.doe@example.com");

        // Click on the "Save" button to add the user
        WebElement saveButton = driver.findElement(By.cssSelector("button.save"));
        saveButton.click();

    }

    @AfterTest
    public void tearDown() {
        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}

package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidationPageTest {
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

        driver.findElement(By.xpath("//p[contains(text(),'Validation')]")).click();

        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testValidationPage() {
        // Verify the presence and correctness of the heading "Validation"
        WebElement validationHeading = driver.findElement(By.xpath("//h1[text()='Validation']"));
        Assert.assertTrue(validationHeading.isDisplayed(), "Validation heading should be displayed");
        Assert.assertEquals(validationHeading.getText(), "Validation", "Validation heading text is incorrect");

        // Verify the presence of the table
        WebElement dataTable = driver.findElement(By.id("dataSets"));
        Assert.assertTrue(dataTable.isDisplayed(), "Data table should be displayed");

        // Verify the presence and correctness of the table headers
        java.util.List<WebElement> headerCells = dataTable.findElements(By.xpath(".//tr/th"));
        Assert.assertEquals(headerCells.size(), 5, "Number of table headers should be 5");

        // Verify the presence of data in the table
        java.util.List<WebElement> dataCells = dataTable.findElements(By.xpath(".//tr/td"));
        Assert.assertTrue(dataCells.size() > 1, "Table should contain data rows (excluding header)");

        // Click on the "Launch" link of the first row
        WebElement launchLink = driver.findElement(By.xpath("//table[@id='dataSets']//tr[2]//span[@class='launch']"));
        launchLink.click();

    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

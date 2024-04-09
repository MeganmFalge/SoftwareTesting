package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DashboardPageTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/rache/Documents/testing/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void Login(){
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
    }


    @Test
    public void testPlaceholdersAndImages() {

        Login();

        // Verify the presence of placeholders and images within the placeholders
        WebElement placeholdersContainer = driver.findElement(By.cssSelector("div.placeholders"));
        Assert.assertTrue(placeholdersContainer.isDisplayed(), "Placeholders container should be displayed");

        // Verify the presence of placeholders and images within them
        java.util.List<WebElement> placeholders = placeholdersContainer.findElements(By.cssSelector("div.placeholder"));
        Assert.assertEquals(placeholders.size(), 2, "There should be two placeholders");

        for (WebElement placeholder : placeholders) {
            WebElement image = placeholder.findElement(By.tagName("img"));
            Assert.assertTrue(image.isDisplayed(), "Image within placeholder should be displayed");
        }
    }


    @Test
    public void testDatasetTable() {
        Login();

        // Verify if the table displaying dataset information is present
        WebElement datasetTable = driver.findElement(By.id("dataSets"));
        Assert.assertTrue(datasetTable.isDisplayed(), "Dataset table should be displayed");

        // Verify if the dataset table is populated with data (assuming datasets are available)
        java.util.List<WebElement> rows = datasetTable.findElements(By.tagName("tr"));
        Assert.assertTrue(rows.size() > 1, "Dataset table should contain data rows");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

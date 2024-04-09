package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManagementPageTest {
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

        driver.findElement(By.xpath("//p[contains(text(),'Management')]")).click();


    }

    public void Login(){


    }

    private void verifyTableHeaders(WebElement table, String[] expectedHeaders) {
        java.util.List<WebElement> headerElements = table.findElements(By.xpath(".//th"));
        Assert.assertEquals(headerElements.size(), expectedHeaders.length, "Number of table headers should match");
        for (int i = 0; i < expectedHeaders.length; i++) {
            Assert.assertEquals(headerElements.get(i).getText(), expectedHeaders[i], "Table header text should match");
        }
    }

    private void verifyTableDataPresent(WebElement table) {
        java.util.List<WebElement> dataCells = table.findElements(By.xpath(".//th"));
        Assert.assertTrue(dataCells.size() > 1, "Table should contain data rows (excluding header)");
    }

    @Test
    public void testDataManagementSection() {
        // Verify the presence and correctness of the heading "Data Management"
        WebElement dataManagementHeading = driver.findElement(By.xpath("//div[@class='ManagementContainer']/h1[text()='Data Management']"));
        Assert.assertTrue(dataManagementHeading.isDisplayed(), "Data Management heading should be displayed");

        // Verify the presence and correctness of table headers for data sets
        WebElement dataSetsTable = driver.findElement(By.xpath("//div[@class='ManagementContainer']//table[@id='dataSets'][1]"));
        verifyTableHeaders(dataSetsTable, new String[]{"Name", "Added", "Source", "Sample Size", "Description", "Actions"});

        // Verify the presence of data in the data sets table
        verifyTableDataPresent(dataSetsTable);
    }

    @Test
    public void testModelManagementSection() {
        // Verify the presence and correctness of the heading "Model Management"
        WebElement modelManagementHeading = driver.findElement(By.xpath("//div[@class='ManagementContainer']/h1[text()='Model Management']"));
        Assert.assertTrue(modelManagementHeading.isDisplayed(), "Model Management heading should be displayed");

        // Verify the presence and correctness of table headers for algorithms
        WebElement algorithmsTable = driver.findElement(By.xpath("//div[@class='ManagementContainer']//table[@id='dataSets'][2]"));
        verifyTableHeaders(algorithmsTable, new String[]{"Name", "Added", "Source", "Description", "Actions"});

        // Verify the presence of data in the algorithms table
        verifyTableDataPresent(algorithmsTable);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NavigationTest {
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
    public void testValidationNavigation() {
        Login();

        // Click on the Validation link
        driver.findElement(By.xpath("//p[contains(text(),'Validation')]")).click();
        // Verify if navigation to /validation occurred
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.endsWith("/validation"), "Navigation to Validation failed");
    }


    @Test
    public void testManagementNavigation() {
        Login();

        // Click on the Management link
        driver.findElement(By.xpath("//p[contains(text(),'Management')]")).click();
        // Verify if navigation to /management occurred
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.endsWith("/management"), "Navigation to Management failed");
    }


    @Test
    public void testDashboardNavigation() {
        Login();

        // Click on the Dashboard link
        driver.findElement(By.xpath("//p[contains(text(),'Dashboard')]")).click();
        // Verify if navigation to /dashboard occurred
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.endsWith("/dashboard"), "Navigation to Dashboard failed");
    }


    @Test
    public void testAdminNavigation() {
        Login();

        // Click on the Dashboard link
        driver.findElement(By.xpath("//p[contains(text(),'Admin')]")).click();
        // Verify if navigation to /dashboard occurred
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.endsWith("/admin"), "Navigation to Dashboard failed");
    }



    @Test
    public void testProjectsNavigation() {
        Login();

        // Click on the Projects link
        driver.findElement(By.xpath("//p[contains(text(),'Projects')]")).click();
        // Verify if navigation to /projects occurred
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.endsWith("/projects"), "Navigation to Projects failed");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


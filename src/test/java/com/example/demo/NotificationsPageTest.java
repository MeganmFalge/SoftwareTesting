package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NotificationsPageTest {

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

        driver.findElement(By.cssSelector("a[name='Notifications']")).click();

    }

    @Test
    public void testAddNotification() {
        // Click on the "add" button to add a notification
        WebElement addButton = driver.findElement(By.xpath("//div[contains(text(), 'add')]"));
        addButton.click();

        // Verify if a notification card is added
        WebElement notificationCard = driver.findElement(By.cssSelector(".cardsContainer mat-card"));
        Assert.assertTrue(notificationCard.isDisplayed(), "Notification card should be added");
    }

    @Test
    public void testRemoveNotification() {
        // Click on the "DISMISS" button to remove a notification
        WebElement dismissButton = driver.findElement(By.cssSelector(".cardsContainer button"));
        dismissButton.click();

        // Verify if the notification card is removed
        try {
            WebElement notificationCard = driver.findElement(By.cssSelector(".cardsContainer mat-card"));
            Assert.fail("Notification card should be removed");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Notification card is removed as expected
        }
    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectPageTest {

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
    public void testToggleCreateProjectSection() {
        Login();

        // Click on the Projects link
        driver.findElement(By.xpath("//p[contains(text(),'Projects')]")).click();

        driver.manage().window().maximize();

        // Click on the "+ New Project" button to toggle the Create New Project section
        driver.findElement(By.cssSelector("button.newbutton")).click();

        // Check if the Create New Project section is visible
        driver.findElement(By.id("createNewProject"));
        Assert.assertTrue(driver.findElement(By.id("createNewProject")).isDisplayed(), "Create New Project section should be visible after clicking the button");

    }


    @Test
    public void testProjectDetailsInTable() {
        Login();

        // Click on the Projects link
        driver.findElement(By.xpath("//p[contains(text(),'Projects')]")).click();

        driver.manage().window().maximize();

        // verify the presence of project names
        WebElement projectTable = driver.findElement(By.id("dataSets"));
        Assert.assertTrue(projectTable.isDisplayed(), "Project table should be visible");

        // Find the rows of the table
        java.util.List<WebElement> rows = projectTable.findElements(By.tagName("tr"));
        // Skip the first row as it contains header
        for (int i = 1; i < rows.size(); i++) {
            // Get the project name from the first column of each row
            WebElement projectNameElement = rows.get(i).findElement(By.xpath("./td[1]"));
            String projectName = projectNameElement.getText();
            // Verify that project name is not empty
            Assert.assertFalse(projectName.isEmpty(), "Project name should not be empty in the table");
        }
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

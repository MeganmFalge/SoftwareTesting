package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class MainPageTest {
    MainPage mainPage = new MainPage();
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


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

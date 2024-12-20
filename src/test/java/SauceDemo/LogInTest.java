package SauceDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInTest {

    public static void main(String[] args) throws InterruptedException {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        driver.navigate().to(" https://www.saucedemo.com");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        String validUsername = "standard_user";
        String validPassword = "secret_sauce";

        String loginPageURL = "https://www.saucedemo.com/";

        usernameField.clear();
        usernameField.sendKeys(validUsername);

        passwordField.clear();
        passwordField.sendKeys(validPassword);

        loginButton.click();

        String expectedURL = "https://www.saucedemo.com/inventory.html";

        Assert.assertNotEquals(driver.getCurrentUrl(), loginPageURL);

        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();

        Thread.sleep(3000);

        WebElement logOutButton = driver.findElement(By.id("logout_sidebar_link"));
        logOutButton.click();

        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);


        //  Test 2


        driver.navigate().to("https://www.saucedemo.com");

        usernameField = driver.findElement(By.id("user-name"));
        passwordField = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.id("login-button"));

        String validUsername1 = "locked_out_user";
        String validPassword1 = "secret_sauce";

        usernameField.clear();
        usernameField.sendKeys(validUsername1);

        passwordField.clear();
        passwordField.sendKeys(validPassword1);

        loginButton.click();

        WebElement LockedOut = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"));
        LockedOut.getText();

        Assert.assertEquals(LockedOut, "Epic sadface: Sorry, this user has been locked out.");

        //  Test 3


        driver.navigate().to("https://www.saucedemo.com");

        usernameField = driver.findElement(By.id("user-name"));
        passwordField = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.id("login-button"));

        String validUsername2 = "visual_user";
        String validPassword2 = "secret_sauce";

        usernameField.clear();
        usernameField.sendKeys(validUsername2);

        passwordField.clear();
        passwordField.sendKeys(validPassword2);

        loginButton.click();

        Assert.assertNotEquals(driver.getCurrentUrl(), loginPageURL);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        menuButton.click();

        Thread.sleep(3000);

        logOutButton.click();

        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);
    }
}

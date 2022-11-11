package hana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
public class TestCase1 {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait;
    @BeforeTest
    public void open_browser(){
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
    }

    @Test (priority = 0)
    public void login() throws InterruptedException {
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
    }

    @Test (priority = 1)
    public void products() throws InterruptedException {
        WebElement product = driver.findElement(By.className("header_secondary_container"));
        Assert.assertTrue(product.isDisplayed());
        WebElement shopping_cart=driver.findElement(By.className("shopping_cart_link"));
        Assert.assertTrue(shopping_cart.isDisplayed());
    }
    @Test (priority = 2)
    public void links() throws InterruptedException {
        WebElement twitter=driver.findElement(By.className("social_twitter"));
        new WebDriverWait(driver,Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOf(twitter));
        boolean twitterStatus=twitter.isDisplayed();
        Assert.assertEquals(twitterStatus,true);

        WebElement facebook=driver.findElement(By.className("social_facebook"));
        new WebDriverWait(driver,Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOf(facebook));
        boolean facebookStatus=facebook.isDisplayed();
        Assert.assertEquals(facebookStatus,true);

        WebElement linkedin=driver.findElement(By.className("social_linkedin"));
        new WebDriverWait(driver,Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOf(linkedin));
        boolean linkedinstatus=linkedin.isDisplayed();
        Assert.assertEquals(linkedinstatus,true);


    }

    @Test (priority = 3)
    public void logout() throws InterruptedException {
        WebElement burger_menu=driver.findElement(By.className("bm-burger-button"));
        burger_menu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        WebElement logout_button=driver.findElement(By.id("logout_sidebar_link"));
        logout_button.click();
    }

    @AfterTest
    public void close_browser(){
        driver.quit();
    }
}







package hana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase2 {


    WebDriver driver=new ChromeDriver();
    WebDriverWait wait;

    @BeforeTest
    public void openbrowser(){
        wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public void login() throws InterruptedException {
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

    }
    @Test(priority = 1)
    public void ClickOnProduct() throws InterruptedException {
        driver.findElement(By.className("inventory_item_name")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

    }
    @Test(priority = 2)
    public void Back(){
        driver.findElement(By.id("back-to-products")).click();
    }
    @Test(priority = 3)
    public void AddToChart() throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();

    }
    @Test(priority = 4)
    public void ShoppingCart() throws InterruptedException {
        driver.findElement(By.className("shopping_cart_link")).click();

    }
    @Test(priority = 5)
    public void CheckOut() throws InterruptedException {
        driver.findElement(By.id("checkout")).click();

    }
    @Test(priority = 6)
    public void InputData() throws InterruptedException {
        driver.findElement(By.id("first-name")).sendKeys("Hana");
        driver.findElement(By.id("last-name")).sendKeys("Maric");
        driver.findElement(By.id("postal-code")).sendKeys("88000");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        WebElement orderMessage=driver.findElement(By.className("complete-header"));
        new WebDriverWait(driver,Duration.ofMillis(2000)).until(ExpectedConditions.visibilityOf(orderMessage));
        boolean orderStatus=orderMessage.isDisplayed();
        Assert.assertEquals(orderStatus,true);
        System.out.println("Confirmation of order verified");
    }
    @Test(priority = 7)
    public void logout() throws InterruptedException {
        WebElement burger_menu=driver.findElement(By.className("bm-burger-button"));
        burger_menu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        WebElement logout_button=driver.findElement(By.id("logout_sidebar_link"));
        logout_button.click();
    }
    @AfterTest
    public void quit(){
        driver.quit();
    }



}

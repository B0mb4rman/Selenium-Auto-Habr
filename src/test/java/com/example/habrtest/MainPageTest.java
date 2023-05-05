package com.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.function.BooleanSupplier;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://habr.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testRaiting() {

        WebElement submitUsers = driver.findElement(By.xpath("//*[contains(text(), 'Авторы')]"));
        submitUsers.click();

        WebElement submitFirstUser = driver.findElement(By.xpath("(//a[@class='tm-user-snippet__title'])[1]"));
        submitFirstUser.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), '1-й')]")).isDisplayed());
    }


    @Test
    public void testLastRaiting() {

        WebElement submitUser = driver.findElement(By.xpath("//*[contains(text(), 'Авторы')]"));
        submitUser.click();

        WebElement submitLastRait = driver.findElement(By.cssSelector("div.tm-pagination__page-group a:last-child"));
        submitLastRait.click();

        WebElement submitLastUser = driver.findElement(By.xpath("(//a[@class='tm-user-snippet__nickname'])[last()]"));
        submitLastUser.click();

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), '100-й')]")).isDisplayed());
    }
}
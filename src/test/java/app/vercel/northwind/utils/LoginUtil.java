package app.vercel.northwind.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginUtil {

    public static void realizarLogin(WebDriver driver) {

        driver.findElement(By.name("email")).sendKeys(TestData.EMAIL_VALIDO);
        driver.findElement(By.name("password")).sendKeys(TestData.SENHA_VALIDA);
        driver.findElement(By.cssSelector("[data-testid='login-button']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("products"));
    }
}
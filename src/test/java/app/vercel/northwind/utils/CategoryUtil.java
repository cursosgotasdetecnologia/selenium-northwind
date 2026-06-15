package app.vercel.northwind.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryUtil {

//    public static void acessarTelaCategorias(WebDriver driver) {
//
//        driver.findElement(By.cssSelector("[data-testid='new-category-button']")).click();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.cssSelector("[data-testid='add-category-btn']")
//        )).click();
//    }
public static void acessarTelaCategorias(WebDriver driver) {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    driver.findElement(By.cssSelector("[data-testid='new-category-button']")).click();

    // Troca para a nova aba aberta
    for (String aba : driver.getWindowHandles()) {
        driver.switchTo().window(aba);
    }

    // Espera o botão ficar clicável e clica
    wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("[data-testid='add-category-btn']")
    )).click();
}
}
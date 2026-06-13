package app.vercel.northwind.login;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.utils.ScreenshotUtil;
import app.vercel.northwind.utils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class LoginTest extends BaseTest{

    @Test
    public void testValidarCamposObrigatoriosVazios() throws IOException {

        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword =  driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.click();
        inputPassword.click();
        btnLogin.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='password-error']"));
        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals("Email e senha são obrigatórios", mensagem.getText());

        ScreenshotUtil.capturar(driver, "campos-obrigatorios");
    }

    @Test
    public void testValidarFormatoEmailInvalido() throws IOException {

        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword =  driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_INVALIDO);
        inputPassword.sendKeys(TestData.SENHA_VALIDA);
        btnLogin.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));
        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals(TestData.MSG_EMAIL_INVALIDO, mensagem.getText());

        ScreenshotUtil.capturar(driver, "email_invalido");
    }

    @Test
    public void testValidarUsuarioNaoCadastrado() throws IOException {

        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword =  driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_INEXISTENTE);
        inputPassword.sendKeys(TestData.SENHA_VALIDA);
        btnLogin.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));
        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals(TestData.MSG_USUARIO_NAO_ENCONTRADO, mensagem.getText());
        ScreenshotUtil.capturar(driver, "usuario_nao_cadastrado");
    }

    @Test
    public void testValidarSenhaIncorreta() throws IOException {
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword =  driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_VALIDO);
        inputPassword.sendKeys(TestData.SENHA_INVALIDA);
        btnLogin.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));
        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals(TestData.MSG_SENHA_INVALIDA, mensagem.getText());
        ScreenshotUtil.capturar(driver, "senha_incorreta");

    }
    @Test
    public void testLoginComSucesso() throws IOException {

        WebElement email = driver.findElement(By.name("email"));
        WebElement senha = driver.findElement(By.name("password"));
        WebElement botao = driver.findElement(By.cssSelector("[data-testid='login-button']"));

        email.sendKeys(TestData.EMAIL_VALIDO);
        senha.sendKeys(TestData.SENHA_VALIDA);

        botao.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.urlToBe("https://northwind-test-platform.vercel.app/products"));

        Assertions.assertEquals("https://northwind-test-platform.vercel.app/products",
                driver.getCurrentUrl()
        );

        ScreenshotUtil.capturar(driver, "caminho_feliz");
    }

}
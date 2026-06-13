package app.vercel.northwind.login;

import app.vercel.northwind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

public class LoginTest extends BaseTest{

    @Test
    public void testValidarCamposObrigatoriosVazios()  {
        driver.get(baseUrl);
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("password")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='password-error']"));
        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals("Email e senha são obrigatórios", mensagem.getText());
    }

    @Test
    public void testValidarFormatoEmailInvalido()  {
        driver.get(baseUrl);
        driver.findElement(By.name("email")).sendKeys("usuario.invalido");
        driver.findElement(By.name("password")).sendKeys("Senha123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));
        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals("Formato de email inválido. Use: nome@dominio.com", mensagem.getText());
    }

    @Test
    public void testValidarUsuarioNaoCadastrado()  {
        driver.get(baseUrl);
        driver.findElement(By.name("email")).sendKeys("usuario123456@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Senha123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));
        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals("Usuário não encontrado. Verifique o email ou cadastre-se.", mensagem.getText());
    }

    @Test
    public void testValidarSenhaIncorreta() {
        driver.get(baseUrl);
        // Preenche email correto e senha incorreta
        driver.findElement(By.name("email")).sendKeys("admin@qatest.com");
        driver.findElement(By.name("password")).sendKeys("SenhaIncorreta@123");
        driver.findElement(By.cssSelector("[data-testid='login-button']")).click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='password-error']"));
        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals("Email ou senha inválidos", mensagem.getText());
    }
}
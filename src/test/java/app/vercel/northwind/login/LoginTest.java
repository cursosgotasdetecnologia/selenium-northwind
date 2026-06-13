package app.vercel.northwind.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;

    private WebDriverWait wait;
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void testValidarCamposObrigatoriosVazios()  {
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("password")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assertions.assertTrue(
        driver.findElement(By.cssSelector("[data-testid='password-error']")).isDisplayed(),"Email e senha são obrigatórios"
        );
    }

    @Test
    public void testValidarFormatoEmailInvalido()  {
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.name("email")).sendKeys("usuario.invalido");
        driver.findElement(By.name("password")).sendKeys("Senha123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assertions.assertTrue(
                driver.findElement(By.cssSelector("[data-testid='email-error']")).isDisplayed(),"Formato de email inválido. Use: nome@dominio.com"
        );
    }

    @Test
    public void testValidarUsuarioNaoCadastrado()  {
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.name("email")).sendKeys("usuario123456@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Senha123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assertions.assertTrue(
                driver.findElement(By.cssSelector("[data-testid='email-error']")).isDisplayed(),"Usuário não encontrado. Verifique o email ou cadastre-se."
        );
    }
    @Test
    public void testValidarSenhaIncorreta() {
        driver.get("https://northwind-test-platform.vercel.app/");

        // Preenche email correto e senha incorreta
        driver.findElement(By.name("email")).sendKeys("admin@qatest.com");
        driver.findElement(By.name("password")).sendKeys("SenhaIncorreta@123");
        driver.findElement(By.cssSelector("[data-testid='login-button']")).click();

        // Valida que a mensagem de erro é exibida no campo de senha
        Assertions.assertTrue(
                driver.findElement(By.cssSelector("[data-testid='password-error']")).isDisplayed(),"Email ou senha inválidos"
        );
    }


    @AfterEach
    public void tearDown()  {
        //driver.quit();
    }
}
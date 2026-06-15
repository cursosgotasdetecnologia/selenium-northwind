package app.vercel.northwind.category;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.utils.CategoryUtil;
import app.vercel.northwind.utils.LoginUtil;
import app.vercel.northwind.utils.ScreenshotUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class CategoryTest extends BaseTest {

    @Test
    @DisplayName("Deve exibir erro ao tentar salvar categoria sem nome")
    public void testNomeObrigatorio() throws IOException {

        LoginUtil.realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        WebElement descricao = driver.findElement(By.cssSelector("[data-testid='category-description-input']"));
        WebElement salvar = driver.findElement(By.cssSelector("[data-testid='save-category-btn']"));

        descricao.sendKeys("Descrição válida com mais de 10 caracteres");
        salvar.click();

        WebElement erro = driver.findElement(By.cssSelector("[data-testid='error-category-name']"));

        Assertions.assertTrue(erro.isDisplayed());
        Assertions.assertEquals("Nome da categoria é obrigatório", erro.getText());

        ScreenshotUtil.capturar(driver, "nome_obrigatorio");
    }

    @Test
    @DisplayName("Deve exibir erro ao tentar salvar categoria sem descrição")
    public void testDescricaoObrigatoria() throws IOException {

        LoginUtil.realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        WebElement nome = driver.findElement(By.cssSelector("[data-testid='category-name-input']"));
        WebElement salvar = driver.findElement(By.cssSelector("[data-testid='save-category-btn']"));

        nome.sendKeys("Categoria Teste6");
        salvar.click();

        WebElement erro = driver.findElement(By.cssSelector("[data-testid='error-category-description']"));

        Assertions.assertTrue(erro.isDisplayed());
        Assertions.assertEquals("Descrição é obrigatória", erro.getText());

        ScreenshotUtil.capturar(driver, "descricao_obrigatoria");
    }

    @Test
    @DisplayName("Deve exibir erro ao informar nome com menos de 2 caracteres")
    public void testNomeInvalido() throws IOException {

        LoginUtil.realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        WebElement nome = driver.findElement(By.cssSelector("[data-testid='category-name-input']"));
        WebElement descricao = driver.findElement(By.cssSelector("[data-testid='category-description-input']"));
        WebElement salvar = driver.findElement(By.cssSelector("[data-testid='save-category-btn']"));

        nome.sendKeys("A");
        descricao.sendKeys("Descrição válida com mais de 10 caracteres");
        salvar.click();

        WebElement erro = driver.findElement(By.cssSelector("[data-testid='error-category-name']"));

        Assertions.assertTrue(erro.isDisplayed());
        Assertions.assertEquals("Deve ter entre 2 e 50 caracteres", erro.getText());

        ScreenshotUtil.capturar(driver, "nome_invalido");
    }

    @Test
    @DisplayName("Deve exibir erro ao informar descrição com menos de 10 caracteres")
    public void testDescricaoInvalida() throws IOException {

        LoginUtil.realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        WebElement nome = driver.findElement(By.cssSelector("[data-testid='category-name-input']"));
        WebElement descricao = driver.findElement(By.cssSelector("[data-testid='category-description-input']"));
        WebElement salvar = driver.findElement(By.cssSelector("[data-testid='save-category-btn']"));

        nome.sendKeys("Categoria Teste");
        descricao.sendKeys("Curta");
        salvar.click();

        WebElement erro = driver.findElement(By.cssSelector("[data-testid='error-category-description']"));

        Assertions.assertTrue(erro.isDisplayed());
        Assertions.assertEquals("Deve ter entre 10 e 200 caracteres", erro.getText());

        ScreenshotUtil.capturar(driver, "descricao_invalida");
    }

    @Test
    @DisplayName("Deve cadastrar categoria com sucesso")
    public void testCadastroCategoriaComSucesso() throws IOException {

        //Realiza Login na aplicação
        LoginUtil.realizarLogin(driver);

        // Acessa tela de categorias (já trata nova aba)
        CategoryUtil.acessarTelaCategorias(driver);

        // Localiza elementos
        WebElement nome = driver.findElement(By.cssSelector("[data-testid='category-name-input']"));
        WebElement descricao = driver.findElement(By.cssSelector("[data-testid='category-description-input']"));
        WebElement salvar = driver.findElement(By.cssSelector("[data-testid='save-category-btn']"));

        // Gera nome único
        String nomeCategoria = "Categoria_" + System.currentTimeMillis();

        nome.sendKeys(nomeCategoria);
        descricao.sendKeys("Descrição válida com mais de 10 caracteres");

        // Clica em salvar
        salvar.click();

        // Espera o toast aparecer
        // Espera o toast aparecer
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Toastify__toast"))
        );

        //  WebElement toast = driver.findElement(By.xpath("//*[text()='Categoria cadastrada com sucesso!']"));

        // Validação
        Assertions.assertTrue(toast.isDisplayed());
        Assertions.assertTrue(toast.getText().contains("Categoria cadastrada com sucesso!"));

        // Evidência
        ScreenshotUtil.capturar(driver, "cadastro_sucesso");
    }


    @Disabled("Teste desnecessário no momento")
    @Test
    @DisplayName("Deve exibir erro ao tentar cadastrar categoria sem estar logado")
    public void testUsuarioSemAutenticacao() throws IOException {

        CategoryUtil.acessarTelaCategorias(driver);

        WebElement nome = driver.findElement(By.cssSelector("[data-testid='category-name-input']"));
        WebElement descricao = driver.findElement(By.cssSelector("[data-testid='category-description-input']"));
        WebElement salvar = driver.findElement(By.cssSelector("[data-testid='save-category-btn']"));

        nome.sendKeys("Categoria Teste");
        descricao.sendKeys("Descrição válida com mais de 10 caracteres");
        salvar.click();

        WebElement mensagem = driver.findElement(By.xpath("//*[text()='Você precisa estar logado']"));

        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals("Você precisa estar logado", mensagem.getText());


    }

    @Test
    @DisplayName("Deve fechar modal ao cancelar cadastro")
    public void testCancelarCadastro() throws IOException {

        LoginUtil.realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        WebElement cancelar = driver.findElement(By.cssSelector("[data-testid='cancel-category-btn']"));
        cancelar.click();

        boolean modalFechado = driver.findElements(By.cssSelector("[data-testid='category-name-input']")).isEmpty();

        Assertions.assertTrue(modalFechado);

        ScreenshotUtil.capturar(driver, "cancelar_cadastro");
    }
}
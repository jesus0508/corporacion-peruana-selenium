package pe.edu.unmsm.corporacion;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pe.edu.unmsm.corporacion.dto.ProveedorDTO;
import pe.edu.unmsm.corporacion.utils.ProveedorDataProvider;

import static org.junit.Assert.*;
import static org.testng.FileAssert.fail;

public class ProveedorModuleTest {
    //public final String DOMAIN = "http://ec2-52-91-116-50.compute-1.amazonaws.com/";
    public final String DOMAIN = "http://corporacion-peruana.test/";
    private WebDriver webDriver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/home/desarrollo/Escritorio/Universidad/Calidad/SegundaParte-Rokys/geckodriver");
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.get(DOMAIN + "login");
    }

    @Test(priority = 1)
    public void testLogin() {
        webDriver.findElement(By.name("email")).sendKeys("corporacion@gmail.com");
        webDriver.findElement(By.name("password")).sendKeys("123456");
        webDriver.findElement(By.xpath("/html/body/div/div[2]/form/div[3]/div/button")).click();
        assertEquals("Bienvenido!", webDriver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div[2]")).getText());
    }

    @Test(priority = 2, dataProvider = "ProveedorDataProvider", dataProviderClass = ProveedorDataProvider.class)
    public void createProveedor(ProveedorDTO proveedorDTO, String responseExpected) {
        webDriver.get(DOMAIN + "proveedores/create");
        webDriver.findElement(By.id("razon_social")).sendKeys(proveedorDTO.razonSocial);
        webDriver.findElement(By.id("ruc")).sendKeys(proveedorDTO.ruc);
        webDriver.findElement(By.id("razon_social")).sendKeys(proveedorDTO.email);
        webDriver.findElement(By.id("linea_credito")).sendKeys(proveedorDTO.lineaCredito);
        webDriver.findElement(By.id("sobregiro")).sendKeys(proveedorDTO.sobregiro);
        webDriver.findElement(By.xpath("/html/body/div/div[1]/section[2]/div/div/form/div/div[3]/button")).click();
        WebElement toast = null;
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            toast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[1]")));
            System.out.println("Se registro con exito");
            assertTrue(toast.isDisplayed());
        } catch (TimeoutException e) {
            System.out.println(e.toString());
            assertNull(toast);
        } catch (Exception e) {
            fail();
        }

    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }

}

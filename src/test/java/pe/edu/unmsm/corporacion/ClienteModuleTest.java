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
import pe.edu.unmsm.corporacion.dto.ClienteDTO;
import pe.edu.unmsm.corporacion.utils.ClienteDataProvider;

import static org.junit.Assert.*;
import static org.testng.FileAssert.fail;

public class ClienteModuleTest {
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

    @Test(priority = 2, dataProvider = "ClienteDataProvider", dataProviderClass = ClienteDataProvider.class)
    public void createClient(ClienteDTO clienteDto, String responseExpected) {
        webDriver.get(DOMAIN + "clientes");
        webDriver.findElement(By.id("ruc")).sendKeys(clienteDto.ruc);
        webDriver.findElement(By.id("representante")).sendKeys(clienteDto.representante);
        webDriver.findElement(By.id("razon_social")).sendKeys(clienteDto.razonSocial);
        webDriver.findElement(By.id("cargo")).sendKeys(clienteDto.cargo);
        webDriver.findElement(By.id("dni")).sendKeys(clienteDto.dni);
        webDriver.findElement(By.id("correo_cliente")).sendKeys(clienteDto.correo);
        webDriver.findElement(By.id("actividad_economica")).sendKeys(clienteDto.actividadEconomica);
        webDriver.findElement(By.id("linea_credito")).sendKeys(clienteDto.lineaCredito);
        webDriver.findElement(By.id("distrito")).sendKeys(clienteDto.distrito);
        webDriver.findElement(By.xpath("/html/body/div/div[1]/section/div[1]/form/div[2]/div/div[2]/div[2]/div[1]/div/textarea")).sendKeys(clienteDto.direccion);
        webDriver.findElement(By.id("precio_galon")).sendKeys(clienteDto.precioGalon);
        webDriver.findElement(By.id("forma_pago")).sendKeys(clienteDto.formaDePago);
        webDriver.findElement(By.id("persona_comision")).sendKeys(clienteDto.personaComision);
        webDriver.findElement(By.id("correo_representante")).sendKeys(clienteDto.correoRepresentante);
        webDriver.findElement(By.xpath("/html/body/div/div[1]/section/div[1]/form/div[3]/div/button")).click();
//        String texto = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]")).getText();
//        System.out.println(texto);
        WebElement toast = null;
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
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

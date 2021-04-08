import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class BlazeTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void blazeTest() {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(1046, 713));
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'London']")).click();
        }
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(5) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Alexandre Rodrigues");
        driver.findElement(By.id("address")).sendKeys("Loteamento do Calvário e Entre Vinhas - lote 31, Lote 31");
        driver.findElement(By.id("city")).sendKeys("Viseu - Penalva do Castelo");
        driver.findElement(By.id("zipCode")).sendKeys("3550 - 112");
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }
}
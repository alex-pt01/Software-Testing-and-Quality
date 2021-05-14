package airquality;


import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FunctionalTests {

    @LocalServerPort
    int randomServerPort;

    private LocalDate today = LocalDate.now();

    @Test
    @Order(0)
    public void cacheInit(ChromeDriver driver) {
        driver.get("http://localhost:" + randomServerPort +"/");
        driver.manage().window().setSize(new Dimension(1382, 834));
        driver.findElement(By.cssSelector(".main_content:nth-child(4) > .input-group-btn span")).click();
        assertThat(driver.findElement(By.cssSelector("td:nth-child(1)")).getText(), is("0"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(2)")).getText(), is("0"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(3)")).getText(), is("0"));
    }

    @Test
    @Order(1)
    void searchLocation1(ChromeDriver driver) {
        driver.get("http://localhost:" + randomServerPort +"/");
        driver.manage().window().setSize(new Dimension(1162, 701));
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Porto");
        driver.findElement(By.cssSelector(".input-group-btn:nth-child(2) span")).click();
        assertThat(driver.findElement(By.cssSelector(".col-md-12 td:nth-child(1)")).getText(), is("Porto"));
        assertThat(driver.findElement(By.cssSelector(".col-md-12 td:nth-child(2)")).getText(), is("41.1475"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(3)")).getText(), is("-8.6588888888889"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(4)")).getText().substring(0,10), is(today.toString()));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(5)")).getText(), is("+01:00"));
    }

    @Test
    @Order(2)
    public void cacheAfter1(ChromeDriver driver) {
        driver.get("http://localhost:" + randomServerPort +"/");
        driver.manage().window().setSize(new Dimension(1382, 834));
        driver.findElement(By.cssSelector(".main_content:nth-child(4) > .input-group-btn span")).click();
        assertThat(driver.findElement(By.cssSelector("td:nth-child(1)")).getText(), is("0"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(2)")).getText(), is("1"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(3)")).getText(), is("1"));
    }

    @Test
    @Order(3)
    void searchLocation2(ChromeDriver driver) {
        driver.get("http://localhost:" + randomServerPort +"/");
        driver.manage().window().setSize(new Dimension(1162, 701));
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Porto");
        driver.findElement(By.cssSelector(".input-group-btn:nth-child(2) span")).click();
        assertThat(driver.findElement(By.cssSelector(".col-md-12 td:nth-child(1)")).getText(), is("Porto"));
        assertThat(driver.findElement(By.cssSelector(".col-md-12 td:nth-child(2)")).getText(), is("41.1475"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(3)")).getText(), is("-8.6588888888889"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(4)")).getText().substring(0,10), is(today.toString()));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(5)")).getText(), is("+01:00"));
    }

    @Test
    @Order(4)
    public void cacheAfter2(ChromeDriver driver) {
        driver.get("http://localhost:" + randomServerPort +"/");
        driver.manage().window().setSize(new Dimension(1382, 834));
        driver.findElement(By.cssSelector(".main_content:nth-child(4) > .input-group-btn span")).click();
        assertThat(driver.findElement(By.cssSelector("td:nth-child(1)")).getText(), is("1"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(2)")).getText(), is("1"));
        assertThat(driver.findElement(By.cssSelector("td:nth-child(3)")).getText(), is("2"));
    }



}
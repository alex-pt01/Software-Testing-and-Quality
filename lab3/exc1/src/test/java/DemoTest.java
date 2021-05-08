import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.*;

class DemoTest {

    WebDriver browser;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/where/you/put/chromedriver");

        FirefoxOptions options = new FirefoxOptions().addPreference("browser.startup.homepage", 255);
        browser = new FirefoxDriver(options);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        browser.close();
    }

    @Test
    public void site_header_is_on_home_page() {
        browser.get("https://www.saucelabs.com");
        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
        assertTrue((href.isDisplayed()));

    }
}
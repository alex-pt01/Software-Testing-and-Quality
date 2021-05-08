import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@ExtendWith(SeleniumJupiter.class)
class FirefoxJupiterTest {

    FirefoxDriver driver;

    //Constructor
    public FirefoxJupiterTest(FirefoxDriver driver) {
        this.driver = driver;
    }

    @Test
    void testWithOneFirefox(FirefoxDriver driver) {
        driver.get("https://www.ua.pt");
        assertThat(driver.getTitle(),
                containsString("Universidade de Aveiro"));
    }

    @Test
    void testWithTwoFirefoxs(FirefoxDriver driver1,
                             FirefoxDriver driver2) {
        driver1.get("http://www.seleniumhq.org/");
        driver2.get("http://junit.org/junit5/");
        assertThat(driver1.getTitle(), startsWith("Selenium"));
        assertThat(driver2.getTitle(), equalTo("JUnit 5"));
    }

    @Test
    void testWithHeadless(HtmlUnitDriver driver) {
        driver.get("https://www.ua.pt");
        assertThat(driver.getTitle(), containsString("Universidade de Aveiro"));


    }

}
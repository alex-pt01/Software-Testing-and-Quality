import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import pages.DeveloperApplyPage;
import pages.HomePage;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplyAsDeveloper {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        //use FF Driver
        driver = new FirefoxDriver();
    }

    @Test
    public void applyAsDeveloper() {
        //Create object of HomePage Class
        HomePage home = new HomePage(driver);
        assertTrue(home.isPageOpened());
        home.clickOnDeveloperApplyButton();

        //Create object of DeveloperPortalPage
        DeveloperApplyPage applyPage= new DeveloperApplyPage(driver);

        //Check if page is opened
        assertTrue(home.isPageOpened());



        //Fill up data
        applyPage.setDeveloper_email("dejan@toptal.com");
        applyPage.setDeveloper_full_name("Dejan Zivanovic Automated Test");
        applyPage.setDeveloper_password("password123");
        applyPage.setDeveloper_password_confirmation("password123");

        //Click on join
        applyPage.clickOnJoin();
    }

}
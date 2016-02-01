package base;

import models.UserFactory.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.GeneralSettingPage;
import pages.HomePage;
import pages.LoginPage;

/**
 * Base test for encapsulating common tests for all tests
 * @author Alex Ilyenko
 */
public abstract class BaseTest {

    protected LoginPage loginPage;
    protected HomePage homePage;
    protected GeneralSettingPage generalSettingsPage;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getWebDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    protected void openLoginPage() {
        driver.get(BasePage.BASE_PATH);
        loginPage = new LoginPage(driver);
    }

    protected void loginToFacebookAs(User user) {
        homePage = loginPage.loginToAccountAs(user);
    }

    private WebDriver getWebDriver() {
        if (driver == null) {
            return new FirefoxDriver();
        }
        return driver;
    }
}

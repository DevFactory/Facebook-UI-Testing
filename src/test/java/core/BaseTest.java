package core;

import models.UserFactory.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.GeneralSettingPage;
import pages.HomePage;
import pages.LoginPage;

/**
 * Base test for encapsulating common logic for all tests
 * @author Alex Ilyenko
 */
public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected GeneralSettingPage generalSettingsPage;
    protected User user;

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
        this.user = user;
        homePage = loginPage.loginToAccountAs(user);
    }

    private WebDriver getWebDriver() {
        if (driver == null) {
            return new FirefoxDriver();
        }
        return driver;
    }
}

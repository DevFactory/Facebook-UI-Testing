package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Home page of the Facebook account
 *
 * @author Alex Ilyenko
 */
public class HomePage extends BasePage {

    @FindBy(id = "pageLoginAnchor")
    @CacheLookup
    private WebElement accountArrow;

    @FindBy(xpath = "//span[contains(text(), 'Settings')]")
    private WebElement settingsOption;

    @FindBy(xpath = "//span[contains(text(), 'Log out')]")
    private WebElement logOutOption;

    HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public GeneralSettingPage goToSettings() {
        accountArrow.click();
        waitForElementToBeVisible(settingsOption, MIN_WAIT_TIMEOUT).click();
        return new GeneralSettingPage(driver);
    }

    public boolean userIsLoggedIn() {
        return isElementDisplayed(accountArrow);
    }

    public LoginPage logOut() {
        accountArrow.click();
        waitForElementToBeVisible(logOutOption, MIN_WAIT_TIMEOUT).click();
        return new LoginPage(driver);
    }

    @Override
    protected HomePage waitForPageToBeLoaded() {
        waitForElementToBeVisible(accountArrow, MAX_WAIT_TIMEOUT);
        return this;
    }
}


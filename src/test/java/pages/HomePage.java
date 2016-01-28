package pages;

import core.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Home page of the Facebook account
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
        waitForElementToBeVisible(settingsOption, 2).click();
        return new GeneralSettingPage(driver);
    }

    public boolean userIsLoggedIn() {
        try {
            return accountArrow.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public LoginPage logOut() {
        accountArrow.click();
        waitForElementToBeVisible(logOutOption, 2).click();
        return new LoginPage(driver);
    }

    @Override
    protected HomePage waitForPageToBeLoaded() {
        waitForElementToBeVisible(accountArrow, 6);
        return this;
    }
}


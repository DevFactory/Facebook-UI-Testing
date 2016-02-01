package pages;

import base.BasePage;
import models.UserFactory.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Login page for Facebook
 * @author Alex Ilyenko
 */
public class LoginPage extends BasePage {

    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailField;

    @FindBy(id = "pass")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(className = "uiButtonConfirm")
    @CacheLookup
    private WebElement loginButton;

    @FindBy(className = "fcb")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForPageToBeLoaded();
    }

    public HomePage loginToAccountAs(User user) {
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        loginButton.click();
        return new HomePage(driver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    @Override
    protected LoginPage waitForPageToBeLoaded() {
        waitForElementToBeVisible(emailField, MIN_WAIT_TIMEOUT);
        return this;
    }
}

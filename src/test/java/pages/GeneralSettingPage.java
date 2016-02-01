package pages;

import base.BasePage;
import models.UserFactory.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * General setting page or https://www.facebook.com/settings
 * @author Alex Ilyenko
 */
public class GeneralSettingPage extends BasePage {

    @FindBy(xpath = "//a[@href='/settings?tab=account&section=name']/span")
    private WebElement editNameButton;

    @FindBy(name = "primary_first_name")
    private WebElement firstNameField;

    @FindBy(xpath = "//button[contains(text(), 'Review Change')]")
    private WebElement reviewChangesButton;

    @FindBy(xpath = "//div[@id='settingsNameForm']//span[@class='uiIconText']")
    private WebElement errorNameChangingMessage;

    @FindBy(id = "save_password")
    private WebElement savePasswordField;

    @FindBy(className = "layerConfirm")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//a[@href='/settings?tab=account&section=name']/span[@class='fcg']")
    private WebElement nameRow;

    GeneralSettingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForPageToBeLoaded();
    }

    public GeneralSettingPage changeFirstNameTo(String newFirstName) {
        editNameButton.click();
        waitForElementToBeVisible(firstNameField, MIN_WAIT_TIMEOUT).clear();
        firstNameField.sendKeys(newFirstName);
        reviewChangesButton.click();
        return this;
    }

    public String getErrorMessage() {
        return errorNameChangingMessage.getText();
    }

    public GeneralSettingPage saveChangesForUser(User user) {
        waitForElementToBeVisible(savePasswordField, MIN_WAIT_TIMEOUT).sendKeys(user.getPassword());
        saveChangesButton.click();
        return this;
    }

    public boolean canChangesBeDone() {
        return isElementDisplayed(saveChangesButton);
    }

    @Override
    protected GeneralSettingPage waitForPageToBeLoaded() {
        waitForElementToBeVisible(editNameButton, MAX_WAIT_TIMEOUT);
        return this;
    }
}




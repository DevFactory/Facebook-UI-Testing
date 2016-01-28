package pages;

import core.BasePage;
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
    private WebElement passwordForSavingChangesField;

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
        waitForElementToBeVisible(firstNameField, 1).clear();
        firstNameField.sendKeys(newFirstName);
        reviewChangesButton.click();
        return this;
    }

    public String getErrorMessage() {
        return errorNameChangingMessage.getText();
    }

    public GeneralSettingPage saveChangesForUser(User user) {
        waitForElementToBeVisible(passwordForSavingChangesField, 2)
                .sendKeys(user.getPassword());
        saveChangesButton.click();
        return this;
    }

    public boolean saveChangesButtonIsVisible() {
        return waitForElementToBeVisible(saveChangesButton, 2).isDisplayed();
    }

    @Override
    protected GeneralSettingPage waitForPageToBeLoaded() {
        waitForElementToBeVisible(editNameButton, 6);
        return this;
    }
}




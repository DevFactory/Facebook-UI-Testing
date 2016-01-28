package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base page for encapsulation common tests for all pages
 * @author Alex Ilyenko
 */
public abstract class BasePage {
    static final String BASE_PATH = "https://www.facebook.com/";
    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    abstract protected BasePage waitForPageToBeLoaded();

    protected WebElement waitForElementToBeVisible(WebElement element, int seconds) {
        return new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.visibilityOf(element));
    }
}

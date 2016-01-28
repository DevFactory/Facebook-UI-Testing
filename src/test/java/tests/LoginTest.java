package tests;

import core.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static core.ErrorMessage.INCORRECT_EMAIL_ADDRESS;
import static core.ErrorMessage.INCORRECT_PASSWORD;
import static core.ErrorMessageMatcher.equalToError;
import static models.UserFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Test class for testing login to FaceBook account action
 *
 * @author Alex Ilyenko
 */
public class LoginTest extends BaseTest {

    @BeforeMethod
    public void openLoginPagePrecondition() {
        openLoginPage();
    }

    @Test
    public void login_WithValidCredentials_ShouldBeSuccessful() {
        loginToFacebookAs(validUser());
        assertTrue(userIsLoggedIn());
        logOut();
    }


    @Test
    public void login_WithInvalidPassword_ShouldNotBeSuccessful() {
        loginToFacebookAs(invalidPasswordUser());
        assertFalse(userIsLoggedIn());
        assertThat(errorMessage(), equalToError(INCORRECT_PASSWORD));
    }

    @Test
    public void login_WithInvalidEmail_ShouldNotBeSuccessful() {
        loginToFacebookAs(invalidEmailUser());
        assertFalse(userIsLoggedIn());
        assertThat(errorMessage(), equalToError(INCORRECT_EMAIL_ADDRESS));
    }

    @Test
    public void login_WithEmptyEmail_ShouldNotBeSuccessful() {
        loginToFacebookAs(emptyEmailUser());
        assertFalse(userIsLoggedIn());
        assertThat(errorMessage(), equalToError(INCORRECT_EMAIL_ADDRESS));
    }

    @Test
    public void login_WithEmptyPassword_ShouldNotBeSuccessful() {
        loginToFacebookAs(emptyPasswordUser());
        assertFalse(userIsLoggedIn());
        assertThat(errorMessage(), equalToError(INCORRECT_PASSWORD));
    }

    private String errorMessage() {
        return loginPage.getErrorMessage();
    }


    private boolean userIsLoggedIn() {
        return homePage.userIsLoggedIn();
    }

    private void logOut() {
        homePage.logOut();
    }

}

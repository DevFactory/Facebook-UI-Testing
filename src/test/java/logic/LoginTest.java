package logic;

import core.BaseTest;
import core.ErrorMessages;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static models.UserFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertFalse;

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
        assertThat("User was not logged in", userIsLoggedIn());
        logOut();
    }


    @Test
    public void login_WithInvalidPassword_ShouldNotBeSuccessful() {
        loginToFacebookAs(invalidPasswordUser());
        assertFalse(userIsLoggedIn());
        assertThat(errorMessage(), equalTo(ErrorMessages.INCORRECT_PASSWORD));
    }

    @Test
    public void login_WithInvalidEmail_ShouldNotBeSuccessful() {
        loginToFacebookAs(invalidEmailUser());
        assertFalse(userIsLoggedIn());
        assertThat(errorMessage(), equalTo(ErrorMessages.INCORRECT_EMAIL_ADDRESS));
    }

    @Test
    public void login_WithEmptyEmail_ShouldNotBeSuccessful() {
        loginToFacebookAs(emptyEmailUser());
        assertFalse(userIsLoggedIn());
        assertThat(errorMessage(), equalTo(ErrorMessages.INCORRECT_EMAIL_ADDRESS));
    }

    @Test
    public void login_WithEmptyPassword_ShouldNotBeSuccessful() {
        loginToFacebookAs(emptyPasswordUser());
        assertFalse(userIsLoggedIn());
        assertThat(errorMessage(), equalTo(ErrorMessages.INCORRECT_PASSWORD));
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

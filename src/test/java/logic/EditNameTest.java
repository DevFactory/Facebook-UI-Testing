package logic;

import core.BaseTest;
import core.ErrorMessages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static models.UserFactory.validUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Test class for testing of the user's first name changing.
 * Note: user name can be changed only once in 60 days, so none of these tests changes the name completely.
 * The main goal of tests is to verify the availability of name changing.
 * <p/>
 * TODO: implement test for user creation for complete name changing in current test suite
 *
 * @author Alex Ilyenko
 */
public class EditNameTest extends BaseTest {
    private static final String VALID_NAME = "Alex";
    private static final String ONLY_SPACES = "        ";
    private static final String NICKNAME = "Superman";
    private static final String NAME_WITH_SYMBOLS = "Alex;";
    private static final String TOO_LONG_NAME = "Alekseyvasilitvitaliymaksimolga";
    private static final String MULTI_ALPHABET_NAME = "AlexАлекс";
    private static final String NAME_WITH_THREE_CAPITALS = "AleksEY";

    @BeforeClass
    public void loginPrecondition() {
        openLoginPage();
        loginToFacebookAs(validUser());
    }

    @BeforeMethod
    public void openSettings() {
        goToSettings();
    }

    @Test
    public void editName_WithValidData_ShouldBeSuccessful() {
        changeFirstNameTo(VALID_NAME);
        assertThat("Change button is not visible", changesCanBeDone());
    }

    /**
     * Test from the task!
     */
    @Test
    public void editName_WithCurrentDate_ShouldNotBeSuccessful() {
        changeFirstNameTo(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()));
        assertThat(errorMessage(), containsString(ErrorMessages.INVALID_CHARACTER_MESSAGE));
    }

    @Test
    public void editName_WithEmptyString_ShouldNotBeSuccessful() {
        changeFirstNameTo(ONLY_SPACES);
        assertThat(errorMessage(), containsString(ErrorMessages.EMPTY_FIRST_NAME_MESSAGE));
    }

    @Test
    public void editName_WithNickname_ShouldNotBeSuccessful() {
        changeFirstNameTo(NICKNAME);
        assertThat(errorMessage(), containsString(ErrorMessages.REAL_NAME_MESSAGE));
    }

    @Test
    public void editName_WithSymbols_ShouldNotBeSuccessful() {
        changeFirstNameTo(NAME_WITH_SYMBOLS);
        assertThat(errorMessage(), containsString(ErrorMessages.INVALID_CHARACTER_MESSAGE));
    }

    @Test
    public void editName_WithTooLongName_ShouldNotBeSuccessful() {
        changeFirstNameTo(TOO_LONG_NAME);
        assertThat(errorMessage(), containsString(ErrorMessages.TOO_LONG_NAME_MESSAGE));
    }

    @Test
    public void editName_WithMultiAlphabet_ShouldNotBeSuccessful() {
        changeFirstNameTo(MULTI_ALPHABET_NAME);
        assertThat(errorMessage(), containsString(ErrorMessages.MULTIPLE_ALPHABET_MESSAGE));
    }

    @Test
    public void editName_MoreThanOneCapitals_ShouldNotBeSuccessful() {
        changeFirstNameTo(NAME_WITH_THREE_CAPITALS);
        assertThat(errorMessage(), containsString(ErrorMessages.MORE_THAN_ONE_CAPITAL_LETTERS_MESSAGE));
    }

    private void goToSettings() {
        generalSettingsPage = homePage.goToSettings();
    }

    private void changeFirstNameTo(String newFirstName) {
        generalSettingsPage.changeFirstNameTo(newFirstName);
    }

    private boolean changesCanBeDone() {
        return generalSettingsPage.saveChangesButtonIsVisible();
    }

    private String errorMessage() {
        return generalSettingsPage.getErrorMessage();
    }

    /**
     * This method actually saves the changes for the user
     *
     * @see logic.EditNameTest
     */
    private void saveChanges() {
        generalSettingsPage.saveChangesForUser(user);
    }
}

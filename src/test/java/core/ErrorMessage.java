package core;

/**
 * Error messages constants
 *
 * @author Alex Ilyenko
 */
public enum ErrorMessage {
    REAL_NAME_MESSAGE("We require everyone to use their real name on Facebook."),
    INVALID_CHARACTER_MESSAGE("The name contains invalid characters."),
    MULTIPLE_ALPHABET_MESSAGE("Your name may not contain characters from more than one alphabet."),
    TOO_LONG_NAME_MESSAGE("This name contains too many characters."),
    MORE_THAN_ONE_CAPITAL_LETTERS_MESSAGE("The name contains too many capital letters."),
    EMPTY_FIRST_NAME_MESSAGE("You must provide a first name."),
    INCORRECT_EMAIL_ADDRESS("Incorrect Email"),
    INCORRECT_PASSWORD("Please re-enter your password");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

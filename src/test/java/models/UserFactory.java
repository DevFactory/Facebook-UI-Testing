package models;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Factory class for user creation
 *
 * @see models.UserFactory.User
 */
public abstract class UserFactory {
    private static final String SPACES = "   ";
    public static final String RESOURCES_CREDENTIALS_PATH = "src/test/resources/credentials.properties";

    /**
     * Creates and returns valid user
     *
     * @return valid {@code User} object
     * @see #readFromProperties(models.UserFactory.Validity)
     */
    public static User validUser() {
        return readFromProperties(Validity.VALID);
    }

    /**
     * Creates and returns user with invalid email
     *
     * @return invalid {@code User} object
     * @see #readFromProperties(models.UserFactory.Validity)
     */
    public static User invalidEmailUser() {
        return readFromProperties(Validity.INVALID_EMAIL);
    }

    /**
     * Creates and returns user with invalid password
     *
     * @return invalid {@code User} object
     * @see #readFromProperties(models.UserFactory.Validity)
     */
    public static User invalidPasswordUser() {
        return readFromProperties(Validity.INVALID_PASSWORD);
    }

    /**
     * Creates and returns user with empty email
     *
     * @return invalid {@code User} object
     * @see #readFromProperties(models.UserFactory.Validity)
     */
    public static User emptyEmailUser() {
        return readFromProperties(Validity.EMPTY_EMAIL);
    }

    /**
     * Creates and returns user with empty password
     *
     * @return invalid {@code User} object
     * @see #readFromProperties(models.UserFactory.Validity)
     */
    public static User emptyPasswordUser() {
        return readFromProperties(Validity.EMPTY_PASSWORD);
    }

    /**
     * Reads user's credentials from property file and creates appropriate user
     *
     * @param validity current case's validity
     * @return {@code User} object
     * @see models.UserFactory.Validity
     * @see #RESOURCES_CREDENTIALS_PATH
     */
    private static User readFromProperties(Validity validity) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(RESOURCES_CREDENTIALS_PATH)) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read properties", e);
        }
        String email = prop.getProperty("validEmail");
        String password = prop.getProperty("validPassword");
        switch (validity) {
            case INVALID_EMAIL:
                email = prop.getProperty("invalidEmail");
                break;
            case INVALID_PASSWORD:
                password = prop.getProperty("invalidPassword");
                break;
            case EMPTY_EMAIL:
                email = SPACES;
                break;
            case EMPTY_PASSWORD:
                password = SPACES;
                break;
        }
        return new User(email, password);
    }

    /**
     * Inner class representing user
     */
    public static class User {
        private String email;
        private String password;

        private User(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    /**
     * Enum representing cases' validity
     */
    private enum Validity {
        VALID,
        INVALID_EMAIL,
        INVALID_PASSWORD,
        EMPTY_EMAIL,
        EMPTY_PASSWORD
    }

}
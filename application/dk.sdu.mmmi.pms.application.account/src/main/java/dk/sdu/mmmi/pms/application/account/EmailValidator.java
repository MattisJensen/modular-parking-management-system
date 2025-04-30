package dk.sdu.mmmi.pms.application.account;

public class EmailValidator {
    /**
     * Validates the email format.
     * @param email the email address to validate
     * @throws IllegalArgumentException if the email format is invalid
     */
    public static void validate(String email) throws IllegalArgumentException {
        String emailRegex = "^[a-zA-Z0-9._%+-]{2,}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
    }
}
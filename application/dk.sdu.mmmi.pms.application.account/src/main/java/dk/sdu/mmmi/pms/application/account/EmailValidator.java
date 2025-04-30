package dk.sdu.mmmi.pms.application.account;

public class EmailValidator {
    /**
     * Validates the email format.
     * @param email the email address to validate
     * @return true if the email format is valid, false otherwise
     */
    public static boolean validate(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]{2,}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
}

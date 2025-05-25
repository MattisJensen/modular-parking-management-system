package dk.sdu.mmmi.pms.application.account.uitl;

public interface AccountValidator {
    /**
     * Authenticates account credentials to generate an authentication token.
     * <p>
     * This method validates the provided email and password to create a JWT token if valid.
     *
     * @param email    the email address of the account
     * @param password the password of the account
     * @return a JWT token string if authentication succeeds or "fail" if authentication fails
     */
    String authenticateAccount(String email, String password);
}

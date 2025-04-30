package dk.sdu.mmmi.pms.application.account.uitl;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.exceptions.DuplicateEmailException;

public class EmailValidator {
    /**
     * Validates the email format.
     *
     * @param email the email address to validate
     * @throws IllegalArgumentException if the email format is invalid
     */
    public static void validateFormat(String email) throws IllegalArgumentException {
        String emailRegex = "^[a-zA-Z0-9._%+-]{2,}@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
    }

    /**
     * Validates that the email is unique.
     *
     * @param email the email address to validate
     * @throws DuplicateEmailException if the email already exists
     */
    public static void validateUniqueness(String email, AccountRepository accountRepository) throws DuplicateEmailException {
        if (accountRepository.findByEmail(email).isPresent()) {
            throw new DuplicateEmailException("An account with the email " + email + " already exists.");
        }
    }
}
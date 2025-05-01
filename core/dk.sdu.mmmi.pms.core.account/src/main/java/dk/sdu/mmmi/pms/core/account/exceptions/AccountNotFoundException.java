package dk.sdu.mmmi.pms.core.account.exceptions;

/**
 * Exception thrown when an {@link dk.sdu.mmmi.pms.core.account.Account} cannot be found.
 * This exception is used to indicate that the requested account does not exist in the system.
 */
public class AccountNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@link AccountNotFoundException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
}
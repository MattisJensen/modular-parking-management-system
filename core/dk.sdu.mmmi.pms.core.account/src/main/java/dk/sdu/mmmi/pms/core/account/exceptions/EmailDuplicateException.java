package dk.sdu.mmmi.pms.core.account.exceptions;

/**
 * Exception thrown when an email address is already in use.
 * This exception is used to indicate that the provided email
 * is not unique and conflicts with an existing {@link dk.sdu.mmmi.pms.core.account.Account}.
 */
public class EmailDuplicateException extends RuntimeException {

    /**
     * Constructs a new {@link EmailDuplicateException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EmailDuplicateException(String message) {
        super(message);
    }
}
package dk.sdu.mmmi.pms.core.account.exceptions;

/**
 * Exception thrown when an email address has an invalid format.
 * This exception is used to indicate that the provided email does not meet
 * the required format for an {@link dk.sdu.mmmi.pms.core.account.Account}.
 */
public class EmailFormatException extends RuntimeException {

    /**
     * Constructs a new {@link EmailFormatException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EmailFormatException(String message) {
        super(message);
    }
}

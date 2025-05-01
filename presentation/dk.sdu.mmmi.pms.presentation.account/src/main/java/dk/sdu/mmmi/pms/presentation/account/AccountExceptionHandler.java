package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.core.account.exceptions.AccountNotFoundException;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailDuplicateException;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for account-related exceptions.
 * This class provides methods to handle specific exceptions and return appropriate HTTP responses.
 */
@RestControllerAdvice
public class AccountExceptionHandler {

    /**
     * Handles {@link EmailFormatException} and returns a Bad Request response.
     *
     * @param ex the {@link EmailFormatException} to handle
     * @return a {@link ResponseEntity} with the error message and HTTP status
     */
    @ExceptionHandler(EmailFormatException.class)
    public ResponseEntity<String> handleEmailFormatException(EmailFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles {@link EmailDuplicateException} and returns a Conflict response.
     *
     * @param ex the {@link EmailDuplicateException} to handle
     * @return a {@link ResponseEntity} with the error message and HTTP status
     */
    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<String> handleDuplicateEmailException(EmailDuplicateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    /**
     * Handles {@link AccountNotFoundException} and returns a Not Found response.
     *
     * @param ex the {@link AccountNotFoundException} to handle
     * @return a {@link ResponseEntity} with the error message and HTTP status
     */
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

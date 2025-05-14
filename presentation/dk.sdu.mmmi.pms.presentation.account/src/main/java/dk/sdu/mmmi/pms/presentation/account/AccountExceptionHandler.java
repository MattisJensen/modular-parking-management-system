package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.core.account.exception.AccountNotFoundException;
import dk.sdu.mmmi.pms.core.account.exception.EmailDuplicateException;
import dk.sdu.mmmi.pms.core.account.exception.EmailFormatException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Global exception handler for account-related exception.
 * This class provides methods to handle specific exception and return appropriate HTTP responses.
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

    /**
     * Handles {@link SignatureException} and returns an Unauthorized response.
     *
     * @param ex the {@link SignatureException} to handle
     * @return a {@link ResponseEntity} with the error message and HTTP status
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignatureException(SignatureException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
    }
}

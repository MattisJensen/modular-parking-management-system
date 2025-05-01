package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.core.account.exceptions.AccountNotFoundException;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailDuplicateException;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionHandler {
    @ExceptionHandler(EmailFormatException.class)
    public ResponseEntity<String> handleEmailFormatException(EmailFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<String> handleDuplicateEmailException(EmailDuplicateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

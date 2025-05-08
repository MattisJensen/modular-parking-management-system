package dk.sdu.mmmi.pms.presentation.booking;

import dk.sdu.mmmi.pms.core.booking.exception.BookingNotFoundException;
import dk.sdu.mmmi.pms.core.booking.exception.BookingTimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for account-related exception.
 * This class provides methods to handle specific exception and return appropriate HTTP responses.
 */
@RestControllerAdvice
public class BookingExceptionHandler {

    /**
     * Handles {@link BookingTimeException} and returns a Bad Request response.
     *
     * @param ex the {@link BookingTimeException} to handle
     * @return a {@link ResponseEntity} with the error message and HTTP status
     */
    @ExceptionHandler(BookingTimeException.class)
    public ResponseEntity<String> handleEmailFormatException(BookingTimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    /**
     * Handles {@link BookingNotFoundException} and returns a Not Found response.
     *
     * @param ex the {@link BookingNotFoundException} to handle
     * @return a {@link ResponseEntity} with the error message and HTTP status
     */
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

package dk.sdu.mmmi.pms.presentation.parkinglot;

import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotException;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for parkinglot-related exception.
 * This class provides methods to handle specific exception and return appropriate HTTP responses.
 */
@RestControllerAdvice
public class ParkingLotExceptionHandler {

    /**
     * Handles {@link ParkingLotException} and returns a Bad Request response.
     *
     * @param ex the {@link ParkingLotException} to handle
     * @return a {@link ResponseEntity} with the error message and HTTP status
     */
    @ExceptionHandler(ParkingLotException.class)
    public ResponseEntity<String> handleParkingLotException(ParkingLotException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ParkingLotNotFoundException.class)
    public ResponseEntity<String> handleParkingLotNotFoundException(ParkingLotNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

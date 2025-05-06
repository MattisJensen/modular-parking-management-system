package dk.sdu.mmmi.pms.presentation.parkingspot;

import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotLimitException;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for parkingspot-related exception.
 * This class provides methods to handle specific exception and return appropriate HTTP responses.
 */
@RestControllerAdvice
public class ParkingSpotExceptionHandler {

    @ExceptionHandler(ParkingSpotLimitException.class)
    public ResponseEntity<String> handleParkingSpotLimitException(ParkingSpotLimitException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ParkingSpotNotFoundException.class)
    public ResponseEntity<String> handleParkingLotNotFoundException(ParkingSpotNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

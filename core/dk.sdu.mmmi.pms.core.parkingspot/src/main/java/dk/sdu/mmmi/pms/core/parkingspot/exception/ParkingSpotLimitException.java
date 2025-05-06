package dk.sdu.mmmi.pms.core.parkingspot.exception;

public class ParkingSpotLimitException extends RuntimeException {
    public ParkingSpotLimitException(String message) {
        super(message);
    }
}

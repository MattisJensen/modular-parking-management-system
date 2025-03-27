package dk.sdu.mmmi.pms.core.booking;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private final UUID id;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final UUID bookedParkingSpotId;
    private final UUID bookedByUserID;

    public Booking(UUID id, LocalDateTime startTime, LocalDateTime endTime, UUID bookedParkingSpotId, UUID bookedByUserID) {
        validateBookingTime(startTime, endTime);
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedParkingSpotId = bookedParkingSpotId;
        this.bookedByUserID = bookedByUserID;
    }

    private void validateBookingTime(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time must be after start time.");
        }
    }

    // Getters
    public UUID getId() { return id; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public UUID getBookedParkingSpotId() { return bookedParkingSpotId; }
    public UUID getBookedByUserID() { return bookedByUserID; }
}

package dk.sdu.mmmi.pms.core.booking;

import java.time.LocalDateTime;
import java.util.UUID;

public record Booking(
        UUID id,
        UUID userId,
        UUID parkingSpotId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BookingStatus bookingStatus
) {
    public Booking {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        if (startTime.isEqual(endTime)) {
            throw new IllegalArgumentException("Booking must have duration");
        }
    }
}
package dk.sdu.mmmi.pms.presentation.booking.datatransferobject;

import java.time.LocalDateTime;
import java.util.UUID;

public record ParkingSpotAvailabilityResponse(
        UUID parkingSpotId,
        LocalDateTime availableFrom,
        LocalDateTime availableUntil
) { }

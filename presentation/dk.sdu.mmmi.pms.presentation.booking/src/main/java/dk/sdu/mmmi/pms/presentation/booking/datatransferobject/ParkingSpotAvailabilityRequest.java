package dk.sdu.mmmi.pms.presentation.booking.datatransferobject;

import java.time.LocalDateTime;
import java.util.UUID;

public record ParkingSpotAvailabilityRequest(
        UUID parkingSpotId,
        LocalDateTime availableSpotsFrom
) {}

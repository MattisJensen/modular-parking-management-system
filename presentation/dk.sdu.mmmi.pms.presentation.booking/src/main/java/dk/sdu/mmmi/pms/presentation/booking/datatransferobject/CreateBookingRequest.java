package dk.sdu.mmmi.pms.presentation.booking.datatransferobject;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateBookingRequest(
        UUID userId,
        UUID parkingSpotId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}
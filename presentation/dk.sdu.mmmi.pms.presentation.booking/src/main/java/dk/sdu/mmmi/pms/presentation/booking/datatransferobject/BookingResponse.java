package dk.sdu.mmmi.pms.presentation.booking.datatransferobject;

import dk.sdu.mmmi.pms.core.booking.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponse(
        UUID id,
        UUID userId,
        UUID parkingSpotId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BookingStatus bookingStatus
) {}
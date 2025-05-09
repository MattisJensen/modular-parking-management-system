package dk.sdu.mmmi.pms.presentation.booking.datatransferobject;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateParkingLotBookingRequest(
        UUID userId,
        UUID parkingLotId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}

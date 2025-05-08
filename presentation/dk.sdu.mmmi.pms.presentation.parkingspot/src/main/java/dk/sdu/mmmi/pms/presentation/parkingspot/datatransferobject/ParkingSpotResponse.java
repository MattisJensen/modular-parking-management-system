package dk.sdu.mmmi.pms.presentation.parkingspot.datatransferobject;

import java.util.UUID;

public record ParkingSpotResponse(
        UUID id,
        UUID parkingLotId,
        String spotIdentifier
) {}

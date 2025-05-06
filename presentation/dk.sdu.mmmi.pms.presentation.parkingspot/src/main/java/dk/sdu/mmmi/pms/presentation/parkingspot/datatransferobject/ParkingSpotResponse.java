package dk.sdu.mmmi.pms.presentation.parkingspot.datatransferobject;

import dk.sdu.mmmi.pms.core.parkingspot.SpotStatus;

import java.util.UUID;

public record ParkingSpotResponse(
        UUID id,
        UUID parkingLotId,
        String spotIdentifier,
        SpotStatus status
) {}

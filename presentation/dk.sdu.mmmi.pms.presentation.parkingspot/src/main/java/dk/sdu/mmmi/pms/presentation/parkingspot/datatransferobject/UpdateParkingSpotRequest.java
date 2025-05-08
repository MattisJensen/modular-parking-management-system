package dk.sdu.mmmi.pms.presentation.parkingspot.datatransferobject;

import java.util.UUID;

public record UpdateParkingSpotRequest(
        UUID parkingLotId,
        String spotIdentifier
) {}

package dk.sdu.mmmi.pms.presentation.parkingspot.datatransferobject;

import java.util.UUID;

public record CreateParkingSpotRequest(
        UUID parkingLotId,
        String identifier
) {}

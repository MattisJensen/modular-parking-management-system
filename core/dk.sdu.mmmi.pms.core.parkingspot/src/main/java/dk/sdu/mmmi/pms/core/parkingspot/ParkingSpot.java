package dk.sdu.mmmi.pms.core.parkingspot;

import java.util.UUID;

public record ParkingSpot(
        UUID id,
        UUID parkingLotId,
        String spotIdentifier,
        SpotStatus status
) {
    public ParkingSpot {
        if (spotIdentifier == null || spotIdentifier.isBlank()) {
            throw new IllegalArgumentException("Spot identifier cannot be blank");
        }
        if (parkingLotId == null) {
            throw new IllegalArgumentException("Must belong to a parking lot");
        }
    }
}
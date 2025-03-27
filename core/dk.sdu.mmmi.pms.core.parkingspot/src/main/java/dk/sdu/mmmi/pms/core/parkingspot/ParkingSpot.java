package dk.sdu.mmmi.pms.core.parkingspot;

import java.util.UUID;

public class ParkingSpot {
    private final UUID id;
    private final int location;
    private final UUID belongsToParkingLotId;

    public ParkingSpot(UUID id, int location, UUID belongsToParkingLotId) {
        validateLocation(location);
        this.id = id;
        this.location = location;
        this.belongsToParkingLotId = belongsToParkingLotId;
    }

    private void validateLocation(int location) {
        if (location < 0) {
            throw new IllegalArgumentException("Invalid location");
        }
    }

    // Getters
    public UUID getId() { return id; }
    public int getLocation() { return location; }
    public UUID getBelongsToParkingLotId() { return belongsToParkingLotId; }
}
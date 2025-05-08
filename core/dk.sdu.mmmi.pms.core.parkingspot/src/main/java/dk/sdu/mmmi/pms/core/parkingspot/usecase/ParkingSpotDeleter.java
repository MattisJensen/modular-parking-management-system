package dk.sdu.mmmi.pms.core.parkingspot.usecase;

import java.util.UUID;

public interface ParkingSpotDeleter {
    void execute(UUID parkingLotId);
}

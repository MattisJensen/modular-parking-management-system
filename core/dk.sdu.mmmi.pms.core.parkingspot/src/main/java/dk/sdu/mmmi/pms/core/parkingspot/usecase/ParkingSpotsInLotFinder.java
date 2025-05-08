package dk.sdu.mmmi.pms.core.parkingspot.usecase;

import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ParkingSpotsInLotFinder {
    List<ParkingSpot> execute(UUID parkingLotId) throws ParkingSpotNotFoundException;
}

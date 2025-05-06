package dk.sdu.mmmi.pms.core.parkinglot;

import java.util.UUID;

public interface ParkingLotFinder {
    ParkingLot execute(UUID id);
}

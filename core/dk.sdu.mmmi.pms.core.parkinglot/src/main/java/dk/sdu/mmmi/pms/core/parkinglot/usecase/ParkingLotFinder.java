package dk.sdu.mmmi.pms.core.parkinglot.usecase;

import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;

import java.util.UUID;

public interface ParkingLotFinder {
    ParkingLot execute(UUID id);
}

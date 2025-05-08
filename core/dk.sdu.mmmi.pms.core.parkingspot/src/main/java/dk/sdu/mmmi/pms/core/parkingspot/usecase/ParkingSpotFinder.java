package dk.sdu.mmmi.pms.core.parkingspot.usecase;


import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;

import java.util.UUID;

public interface ParkingSpotFinder {
    /**
     * Finds a parking spot by its ID.
     *
     * @param id the ID of the parking spot
     * @return the parking spot with the given ID, or null if not found
     * @throws ParkingSpotNotFoundException if the parking spot is not found
     */
    ParkingSpot execute(UUID id) throws ParkingSpotNotFoundException;
}

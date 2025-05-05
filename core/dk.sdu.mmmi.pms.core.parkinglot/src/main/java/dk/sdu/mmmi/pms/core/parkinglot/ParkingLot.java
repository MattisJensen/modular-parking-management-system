package dk.sdu.mmmi.pms.core.parkinglot;

import java.util.UUID;

/**
 * Domain entity representing a parking lot
 * @param id Unique identifier
 * @param name Human-readable name
 * @param location Geographical location
 * @param capacity Maximum number of vehicles
 * @param availableSpots Currently available spots
 */
public record ParkingLot(
        UUID id,
        String name,
        String location,
        int capacity,
        int availableSpots
) {
    public ParkingLot {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        if (availableSpots < 0 || availableSpots > capacity) {
            throw new IllegalArgumentException("Available spots must be between 0 and capacity");
        }
    }
}
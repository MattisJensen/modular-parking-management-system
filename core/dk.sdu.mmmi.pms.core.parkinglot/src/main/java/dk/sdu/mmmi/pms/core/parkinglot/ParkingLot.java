package dk.sdu.mmmi.pms.core.parkinglot;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ParkingLot {
    private final UUID id;
    private final String name;
    private final List<UUID> parkingSpotIds; // Renamed for clarity

    public ParkingLot(UUID id, String name, List<UUID> parkingSpotIds) {
        this.id = id;
        this.name = name.strip();
        this.parkingSpotIds = List.copyOf(parkingSpotIds); // Defensive copy
    }

    public int getTotalParkingSpots() {
        return parkingSpotIds.size();
    }

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public List<UUID> getParkingSpotIds() { return Collections.unmodifiableList(parkingSpotIds); }
}
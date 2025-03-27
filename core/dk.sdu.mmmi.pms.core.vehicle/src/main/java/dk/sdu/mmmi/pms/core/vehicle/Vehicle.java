package dk.sdu.mmmi.pms.core.vehicle;

import java.util.UUID;

public class Vehicle {
    private final UUID id;
    private final String licensePlate;

    public Vehicle(UUID id, String licensePlate) {
        this.id = id;
        this.licensePlate = licensePlate;
    }

    // Getters
    public UUID getId() { return id; }
    public String getLicensePlate() { return licensePlate; }
}

package dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "parking_lots")
public class ParkingLotJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int availableSpots;

    protected ParkingLotJpaEntity() {}

    public ParkingLotJpaEntity(UUID id, String name, String location, int capacity, int availableSpots) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.availableSpots = availableSpots;
    }

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public int getAvailableSpots() { return availableSpots; }
}
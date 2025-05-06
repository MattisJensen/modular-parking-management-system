package dk.sdu.mmmi.pms.infrastructure.parkingspot.persistence;

import dk.sdu.mmmi.pms.core.parkingspot.SpotStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "parking_spots")
public class ParkingSpotJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID parkingLotId;

    @Column(nullable = false)
    private String spotIdentifier;

    @Column(nullable = false)
    private int availableSpots;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotStatus status;

    protected ParkingSpotJpaEntity() {}

    public ParkingSpotJpaEntity(UUID id, UUID parkingLotId, String spotIdentifier, SpotStatus status) {
        this.id = id;
        this.parkingLotId = parkingLotId;
        this.spotIdentifier = spotIdentifier;
        this.status = status;
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getParkingLotId() { return parkingLotId; }
    public String getSpotIdentifier() { return spotIdentifier; }
    public SpotStatus getStatus() { return status; }
    public int getAvailableSpots() { return availableSpots; }
}
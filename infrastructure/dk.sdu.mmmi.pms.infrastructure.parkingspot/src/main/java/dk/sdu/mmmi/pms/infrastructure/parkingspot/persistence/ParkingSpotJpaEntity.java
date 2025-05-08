package dk.sdu.mmmi.pms.infrastructure.parkingspot.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    protected ParkingSpotJpaEntity() {}

    public ParkingSpotJpaEntity(UUID id, UUID parkingLotId, String spotIdentifier) {
        this.id = id;
        this.parkingLotId = parkingLotId;
        this.spotIdentifier = spotIdentifier;
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getParkingLotId() { return parkingLotId; }
    public String getSpotIdentifier() { return spotIdentifier; }
}
package dk.sdu.mmmi.pms.infrastructure.parkingspot.persistence;

import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotMapper {
    public ParkingSpotJpaEntity toJpaEntity(ParkingSpot core) {
        return new ParkingSpotJpaEntity(
                core.id(),
                core.parkingLotId(),
                core.spotIdentifier()
        );
    }

    public ParkingSpot toCore(ParkingSpotJpaEntity jpa) {
        return new ParkingSpot(
                jpa.getId(),
                jpa.getParkingLotId(),
                jpa.getSpotIdentifier()
        );
    }
}

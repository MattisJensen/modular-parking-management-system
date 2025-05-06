package dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence;

import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {
    public ParkingLotJpaEntity toJpaEntity(ParkingLot core) {
        return new ParkingLotJpaEntity(
                core.id(),
                core.name(),
                core.location(),
                core.capacity(),
                core.availableSpots()
        );
    }

    public ParkingLot toCore(ParkingLotJpaEntity jpa) {
        return new ParkingLot(
                jpa.getId(),
                jpa.getName(),
                jpa.getLocation(),
                jpa.getCapacity(),
                jpa.getAvailableSpots()
        );
    }
}

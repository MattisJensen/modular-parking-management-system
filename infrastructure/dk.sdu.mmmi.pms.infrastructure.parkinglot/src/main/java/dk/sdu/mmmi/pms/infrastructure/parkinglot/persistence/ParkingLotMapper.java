package dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence;

import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {
    public ParkingLotJpaEntity toJpaEntity(ParkingLot domain) {
        return new ParkingLotJpaEntity(
                domain.id(),
                domain.name(),
                domain.location(),
                domain.capacity(),
                domain.availableSpots()
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

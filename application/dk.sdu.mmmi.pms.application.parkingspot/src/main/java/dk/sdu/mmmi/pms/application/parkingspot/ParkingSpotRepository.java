package dk.sdu.mmmi.pms.application.parkingspot;

import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParkingSpotRepository {
    void save(ParkingSpot parkingSpot);
    void update(ParkingSpot parkingSpot);
    void deleteById(UUID id);
    Optional<ParkingSpot> findById(UUID id);
    List<ParkingSpot> findByParkingLotId(UUID parkingLotId);
}

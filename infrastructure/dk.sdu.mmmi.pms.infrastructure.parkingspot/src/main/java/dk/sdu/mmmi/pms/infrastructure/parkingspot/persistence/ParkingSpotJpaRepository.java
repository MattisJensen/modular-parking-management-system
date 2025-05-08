package dk.sdu.mmmi.pms.infrastructure.parkingspot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParkingSpotJpaRepository extends JpaRepository<ParkingSpotJpaEntity, UUID> {
    List<ParkingSpotJpaEntity> findByParkingLotId(UUID parkingLotId);
}

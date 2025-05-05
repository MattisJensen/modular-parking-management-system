package dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingLotJpaRepository extends JpaRepository<ParkingLotJpaEntity, UUID> {}

package dk.sdu.mmmi.pms.infrastructure.parkingspot;

import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.SpotStatus;
import dk.sdu.mmmi.pms.infrastructure.parkingspot.persistence.ParkingSpotJpaEntity;
import dk.sdu.mmmi.pms.infrastructure.parkingspot.persistence.ParkingSpotJpaRepository;
import dk.sdu.mmmi.pms.infrastructure.parkingspot.persistence.ParkingSpotMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {
    private final ParkingSpotJpaRepository springDataRepo;
    private final ParkingSpotMapper mapper;

    public ParkingSpotRepositoryImpl(ParkingSpotJpaRepository springDataRepo, ParkingSpotMapper mapper) {
        this.springDataRepo = springDataRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(ParkingSpot parkingSpot) {
        ParkingSpotJpaEntity jpaEntity = mapper.toJpaEntity(parkingSpot);
        springDataRepo.save(jpaEntity);
    }

    @Override
    public void update(ParkingSpot parkingSpot) {
        ParkingSpotJpaEntity jpaEntity = mapper.toJpaEntity(parkingSpot);
        springDataRepo.save(jpaEntity);
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepo.deleteById(id);
    }

    @Override
    public Optional<ParkingSpot> findById(UUID id) {
        return springDataRepo.findById(id).map(mapper::toCore);
    }

    @Override
    public List<ParkingSpot> findByParkingLotId(UUID parkingLotId) {
        return springDataRepo.findByParkingLotId(parkingLotId)
                .stream()
                .map(mapper::toCore)
                .toList();
    }

    @Override
    public List<ParkingSpot> findAvailableSpotsByParkingLotId(UUID parkingLotId) {
        return springDataRepo.findByParkingLotIdAndStatus(parkingLotId, SpotStatus.AVAILABLE)
                .stream()
                .map(mapper::toCore)
                .toList();
    }
}

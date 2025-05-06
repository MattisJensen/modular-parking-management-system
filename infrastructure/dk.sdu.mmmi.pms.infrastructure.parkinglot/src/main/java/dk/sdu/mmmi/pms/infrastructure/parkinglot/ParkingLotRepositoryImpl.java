package dk.sdu.mmmi.pms.infrastructure.parkinglot;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence.ParkingLotJpaEntity;
import dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence.ParkingLotJpaRepository;
import dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence.ParkingLotMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ParkingLotRepositoryImpl implements ParkingLotRepository {
    private final ParkingLotJpaRepository springDataRepo;
    private final ParkingLotMapper mapper;

    public ParkingLotRepositoryImpl(ParkingLotJpaRepository springDataRepo, ParkingLotMapper mapper) {
        this.springDataRepo = springDataRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(ParkingLot parkingLot) {
        ParkingLotJpaEntity jpaEntity = mapper.toJpaEntity(parkingLot);
        springDataRepo.save(jpaEntity);
    }

    @Override
    public void update(ParkingLot parkingLot) {
        ParkingLotJpaEntity jpaEntity = mapper.toJpaEntity(parkingLot);
        springDataRepo.save(jpaEntity);
    }

    @Override
    public Optional<ParkingLot> findById(UUID id) {
        return springDataRepo.findById(id).map(mapper::toCore);
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepo.deleteById(id);
    }

    @Override
    public Iterable<ParkingLot> findAll() {
        return springDataRepo.findAll().stream()
                .map(mapper::toCore)
                .toList();
    }
}

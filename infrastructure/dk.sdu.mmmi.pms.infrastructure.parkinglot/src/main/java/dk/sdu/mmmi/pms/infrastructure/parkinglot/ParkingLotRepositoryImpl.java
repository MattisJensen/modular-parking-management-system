package dk.sdu.mmmi.pms.infrastructure.parkinglot;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence.ParkingLotJpaEntity;
import dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence.ParkingLotJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ParkingLotRepositoryImpl implements ParkingLotRepository {
    private final ParkingLotJpaRepository springDataRepo;

    public ParkingLotRepositoryImpl(ParkingLotJpaRepository springDataRepo) {
        this.springDataRepo = springDataRepo;
    }

    private ParkingLotJpaEntity toJpaEntity(ParkingLot domain) {
        return new ParkingLotJpaEntity(
                domain.id(),
                domain.name(),
                domain.location(),
                domain.capacity(),
                domain.availableSpots()
        );
    }

    private ParkingLot toDomain(ParkingLotJpaEntity jpa) {
        return new ParkingLot(
                jpa.getId(),
                jpa.getName(),
                jpa.getLocation(),
                jpa.getCapacity(),
                jpa.getAvailableSpots()
        );
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        return toDomain(springDataRepo.save(toJpaEntity(parkingLot)));
    }

    @Override
    public Optional<ParkingLot> findById(UUID id) {
        return springDataRepo.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepo.deleteById(id);
    }

    @Override
    public Iterable<ParkingLot> findAll() {
        return springDataRepo.findAll().stream()
                .map(this::toDomain)
                .toList();
    }
}

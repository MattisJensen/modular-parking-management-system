package dk.sdu.mmmi.pms.application.parkinglot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotNotFoundException;

import java.util.UUID;

public class FindParkingLotByIdUseCase {
    private final ParkingLotRepository repository;

    public FindParkingLotByIdUseCase(ParkingLotRepository repository) {
        this.repository = repository;
    }

    public ParkingLot execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ParkingLotNotFoundException("Parking lot not found with id: " + id));
    }
}
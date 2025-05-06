package dk.sdu.mmmi.pms.application.parkinglot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotNotFoundException;

import java.util.UUID;

public class DeleteParkingLotUseCase {
    private final ParkingLotRepository repository;

    public DeleteParkingLotUseCase(ParkingLotRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        if (repository.findById(id).isEmpty()) throw new ParkingLotNotFoundException("Parking lot not found with id: " + id);
        repository.deleteById(id);
    }
}
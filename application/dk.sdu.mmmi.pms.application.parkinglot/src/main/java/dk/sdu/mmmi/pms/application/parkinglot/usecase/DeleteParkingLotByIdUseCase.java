package dk.sdu.mmmi.pms.application.parkinglot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotNotFoundException;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpotDeleter;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;

import java.util.UUID;

public class DeleteParkingLotByIdUseCase {
    private final ParkingLotRepository repository;
    private final ParkingSpotDeleter deleteAllSpotsUseCase;

    public DeleteParkingLotByIdUseCase(ParkingLotRepository repository, ParkingSpotDeleter deleteAllSpotsUseCase) {
        this.repository = repository;
        this.deleteAllSpotsUseCase = deleteAllSpotsUseCase;
    }

    public void execute(UUID id) {
        if (repository.findById(id).isEmpty()) throw new ParkingLotNotFoundException("Parking lot not found with id: " + id);
        try {
            deleteAllSpotsUseCase.execute(id);
        } catch (ParkingSpotNotFoundException e) {} // Ignore this exception, the parking lot should get deleted anyway.
        repository.deleteById(id);
    }
}
package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;

import java.util.UUID;

public class DeleteParkingSpotByIdUseCase {
    private final ParkingSpotRepository repository;

    public DeleteParkingSpotByIdUseCase(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        if (repository.findById(id).isEmpty()) throw new ParkingSpotNotFoundException("Parking spot with id " + id + " not found");
        repository.deleteById(id);
    }
}

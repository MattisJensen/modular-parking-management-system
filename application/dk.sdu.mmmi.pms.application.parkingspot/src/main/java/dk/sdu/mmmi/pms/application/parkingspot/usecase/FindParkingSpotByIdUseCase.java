package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.usecase.FindParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;

import java.util.UUID;

public class FindParkingSpotByIdUseCase {
    private final ParkingSpotRepository repository;

    public FindParkingSpotByIdUseCase(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    public ParkingSpot execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ParkingSpotNotFoundException("Parking lot not found with id: " + id));
    }
}

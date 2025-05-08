package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotFinder;

import java.util.UUID;

public class FindParkingSpotByIdUseCase implements ParkingSpotFinder {
    private final ParkingSpotRepository repository;

    public FindParkingSpotByIdUseCase(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    @Override
    public ParkingSpot execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ParkingSpotNotFoundException("Parking spot not found with id: " + id));
    }
}

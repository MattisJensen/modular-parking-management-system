package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;

import java.util.UUID;

public class UpdateParkingSpotUseCase {
    private final ParkingSpotRepository repository;

    public UpdateParkingSpotUseCase(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, UpdateParameters parameters) {
        ParkingSpot existing = repository.findById(id).orElseThrow(() -> new ParkingSpotNotFoundException("Parking spot not found with id: " + id));

        ParkingSpot updatedParkingSpot = createUpdatedParkingSpot(existing, parameters);
        repository.update(updatedParkingSpot);
    }

    private ParkingSpot createUpdatedParkingSpot(ParkingSpot existing, UpdateParameters parameters) {
        return new ParkingSpot(
                existing.id(),
                parameters.parkingLotId() != null ? parameters.parkingLotId() : existing.parkingLotId(),
                parameters.spotIdentifier() != null ? parameters.spotIdentifier() : existing.spotIdentifier()
        );
    }

    public record UpdateParameters(
            UUID parkingLotId,
            String spotIdentifier
    ) {}
}
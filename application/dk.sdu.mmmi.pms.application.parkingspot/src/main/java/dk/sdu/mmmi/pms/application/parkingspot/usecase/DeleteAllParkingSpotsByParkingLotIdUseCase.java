package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotDeleter;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;

import java.util.List;
import java.util.UUID;

public class DeleteAllParkingSpotsByParkingLotIdUseCase implements ParkingSpotDeleter {
    private final ParkingSpotRepository repository;

    public DeleteAllParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID parkingLotId) {
        List<ParkingSpot> spots = repository.findByParkingLotId(parkingLotId);
        if (spots.isEmpty()) throw new ParkingSpotNotFoundException("Parking lot with ID " + parkingLotId + " does not have any parking spots.");

        for (ParkingSpot spot : spots) {
            repository.deleteById(spot.id());
        }
    }
}

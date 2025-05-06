package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.usecase.FindParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.SpotStatus;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotLimitException;

import java.util.UUID;

public class CreateParkingSpotUseCase {
    private final ParkingSpotRepository repository;
    private final FindParkingLotByIdUseCase findParkingLotByIdUseCase;

    public CreateParkingSpotUseCase(ParkingSpotRepository repository, FindParkingLotByIdUseCase findParkingLotByIdUseCase) {
        this.repository = repository;
        this.findParkingLotByIdUseCase = findParkingLotByIdUseCase;
    }

    public UUID createSpot(UUID parkingLotId, String identifier) {
        // Check if the parking lot exists
        ParkingLot parkingLot = findParkingLotByIdUseCase.execute(parkingLotId);

        // Check if the parking lot has reached its capacity
        int currentAssignedParkingSpots = getCurrentAssignedParkingSpotsInParkingLot(parkingLotId);
        if (currentAssignedParkingSpots >= parkingLot.capacity()) throw new ParkingSpotLimitException("Parking lot with id: " + parkingLotId + " has already reached its capacity of: " + parkingLot.capacity());

        // Create a new parking spot
        UUID spotId = UUID.randomUUID();
        ParkingSpot spot = new ParkingSpot(
                spotId, parkingLotId, identifier, SpotStatus.AVAILABLE
        );
        repository.save(spot);
        return spotId;
    }

    private int getCurrentAssignedParkingSpotsInParkingLot(UUID parkingLotId) {
        return repository.findByParkingLotId(parkingLotId).size();
    }
}

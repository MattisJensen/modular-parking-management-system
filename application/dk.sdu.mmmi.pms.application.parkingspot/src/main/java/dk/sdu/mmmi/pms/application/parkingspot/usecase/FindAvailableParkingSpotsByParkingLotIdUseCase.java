package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.usecase.FindParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;

import java.util.List;
import java.util.UUID;

public class FindAvailableParkingSpotsByParkingLotIdUseCase {
    private final ParkingSpotRepository repository;
    private final FindParkingLotByIdUseCase findParkingLotByIdUseCase;

    public FindAvailableParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository, FindParkingLotByIdUseCase findParkingLotByIdUseCase) {
        this.repository = repository;
        this.findParkingLotByIdUseCase = findParkingLotByIdUseCase;
    }

    public List<ParkingSpot> execute(UUID parkingLotId) {
        findParkingLotByIdUseCase.execute(parkingLotId);
        List<ParkingSpot> result = repository.findAvailableSpotsByParkingLotId(parkingLotId);
        if (result.isEmpty()) throw new ParkingSpotNotFoundException("Parking lot with ID " + parkingLotId + " does not have any available parking spots.");
        return result;
    }
}

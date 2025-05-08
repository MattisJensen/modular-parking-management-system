package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.usecase.ParkingLotFinder;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotNotFoundException;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;

import java.util.List;
import java.util.UUID;

public class FindParkingSpotsByParkingLotIdUseCase {
    private final ParkingSpotRepository repository;
    private final ParkingLotFinder findParkingLotByIdUseCase;

    public FindParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository, ParkingLotFinder findParkingLotByIdUseCase) {
        this.repository = repository;
        this.findParkingLotByIdUseCase = findParkingLotByIdUseCase;
    }

    public List<ParkingSpot> execute(UUID parkingLotId) {
        findParkingLotByIdUseCase.execute(parkingLotId);
        List<ParkingSpot> result = repository.findByParkingLotId(parkingLotId);
        if (result.isEmpty()) throw new ParkingLotNotFoundException("Parking lot with ID " + parkingLotId + " does not have any parking spots.");
        return result;
    }
}

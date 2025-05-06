package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.usecase.FindParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotNotFoundException;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;

import java.util.List;
import java.util.UUID;

public class FindParkingSpotsByParkingLotIdUseCase {
    private final ParkingSpotRepository repository;
    private final FindParkingLotByIdUseCase findParkingLotByIdUseCase;

    public FindParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository, FindParkingLotByIdUseCase findParkingLotByIdUseCase) {
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

package dk.sdu.mmmi.pms.application.parkingspot.usecase;

import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.usecase.ParkingLotFinder;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.exception.ParkingSpotNotFoundException;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotsInLotFinder;

import java.util.List;
import java.util.UUID;

public class FindParkingSpotsByParkingLotIdUseCase implements ParkingSpotsInLotFinder {
    private final ParkingSpotRepository repository;
    private final ParkingLotFinder findParkingLotByIdUseCase;

    public FindParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository, ParkingLotFinder findParkingLotByIdUseCase) {
        this.repository = repository;
        this.findParkingLotByIdUseCase = findParkingLotByIdUseCase;
    }

    @Override
    public List<ParkingSpot> execute(UUID parkingLotId) {
        findParkingLotByIdUseCase.execute(parkingLotId);
        List<ParkingSpot> result = repository.findByParkingLotId(parkingLotId);
        if (result.isEmpty()) throw new ParkingSpotNotFoundException("Parking lot with ID " + parkingLotId + " does not have any parking spots.");
        return result;
    }
}

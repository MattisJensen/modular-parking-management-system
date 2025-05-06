package dk.sdu.mmmi.pms.application.parkinglot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotException;

import java.util.UUID;

public class UpdateParkingLotUseCase {
    private final ParkingLotRepository repository;
    private final GetParkingLotUseCase getUseCase;

    public UpdateParkingLotUseCase(ParkingLotRepository repository, GetParkingLotUseCase getUseCase) {
        this.repository = repository;
        this.getUseCase = getUseCase;
    }

    public void execute(UUID id, UpdateParameters parameters) {
        ParkingLot existing = getUseCase.execute(id);
        if (parameters.capacity != null) validateCapacity(existing, parameters);

        ParkingLot updatedParkingLot = createUpdatedParkingLot(existing, parameters);
        repository.update(updatedParkingLot);
    }

    private ParkingLot createUpdatedParkingLot(ParkingLot existing, UpdateParameters params) {
        return new ParkingLot(
                existing.id(),
                params.name() != null ? params.name() : existing.name(),
                params.location() != null ? params.location() : existing.location(),
                params.capacity() != null ? params.capacity() : existing.capacity(),
                params.availableSpots() != null ? params.availableSpots() : existing.availableSpots()
        );
    }

    private void validateCapacity(ParkingLot existing, UpdateParameters params) throws ParkingLotException {
        Integer newCapacity = params.capacity();

        int occupiedSpots = existing.capacity() - existing.availableSpots();
        if (newCapacity < occupiedSpots) throw new ParkingLotException("New capacity " + newCapacity + " cannot be less than occupied spots " + occupiedSpots);
    }

    public record UpdateParameters(
            String name,
            String location,
            Integer capacity,
            Integer availableSpots
    ) {
    }
}
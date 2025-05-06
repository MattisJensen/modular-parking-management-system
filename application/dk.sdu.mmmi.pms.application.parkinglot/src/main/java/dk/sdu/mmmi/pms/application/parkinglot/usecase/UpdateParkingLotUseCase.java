package dk.sdu.mmmi.pms.application.parkinglot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotException;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotNotFoundException;

import java.util.UUID;

public class UpdateParkingLotUseCase {
    private final ParkingLotRepository repository;

    public UpdateParkingLotUseCase(ParkingLotRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, UpdateParameters parameters) {
        ParkingLot existing = repository.findById(id).orElseThrow(() -> new ParkingLotNotFoundException("Parking lot not found with id: " + id));
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
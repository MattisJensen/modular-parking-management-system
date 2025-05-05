package dk.sdu.mmmi.pms.application.parkinglot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotException;
import java.util.UUID;

public class CreateParkingLotUseCase {
    private final ParkingLotRepository repository;

    public CreateParkingLotUseCase(ParkingLotRepository repository) {
        this.repository = repository;
    }

    public UUID execute(String name, String location, int capacity) {
        if (capacity <= 0) {
            throw new ParkingLotException("Capacity must be positive");
        }

        ParkingLot newLot = new ParkingLot(
                UUID.randomUUID(),
                name,
                location,
                capacity,
                capacity // Initialize with full availability
        );

        return repository.save(newLot).id();
    }
}
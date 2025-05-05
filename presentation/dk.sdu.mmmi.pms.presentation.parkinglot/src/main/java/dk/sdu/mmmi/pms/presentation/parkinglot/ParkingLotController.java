package dk.sdu.mmmi.pms.presentation.parkinglot;

import dk.sdu.mmmi.pms.application.parkinglot.usecase.CreateParkingLotUseCase;
import dk.sdu.mmmi.pms.presentation.parkinglot.datatransferobject.CreateParkingLotRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/parking-lot")
public class ParkingLotController {
    private final CreateParkingLotUseCase createUseCase;

    public ParkingLotController(CreateParkingLotUseCase createUseCase) {
        this.createUseCase = createUseCase;
    }

    @PostMapping
    public ResponseEntity<UUID> createParkingLot(@RequestBody CreateParkingLotRequest request) {
        UUID id = createUseCase.execute(
                request.name(),
                request.location(),
                request.capacity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
package dk.sdu.mmmi.pms.presentation.parkinglot;

import dk.sdu.mmmi.pms.application.parkinglot.usecase.CreateParkingLotUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.DeleteParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.FindParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.UpdateParkingLotUseCase;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.presentation.parkinglot.datatransferobject.CreateParkingLotRequest;
import dk.sdu.mmmi.pms.presentation.parkinglot.datatransferobject.ParkingLotResponse;
import dk.sdu.mmmi.pms.presentation.parkinglot.datatransferobject.UpdateParkingLotRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/parking-lot")
public class ParkingLotController {
    private final CreateParkingLotUseCase createUseCase;
    private final UpdateParkingLotUseCase updateUseCase;
    private final FindParkingLotByIdUseCase findByIdUseCase;
    private final DeleteParkingLotByIdUseCase deleteUseCase;

    public ParkingLotController(CreateParkingLotUseCase createUseCase,
                                UpdateParkingLotUseCase updateUseCase,
                                FindParkingLotByIdUseCase findByIdUseCase,
                                DeleteParkingLotByIdUseCase deleteUseCase) {
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<UUID> createParkingLot(@RequestBody CreateParkingLotRequest request) {
        UUID lotId = createUseCase.execute(
                request.name(),
                request.location(),
                request.capacity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(lotId);
    }

    /**
     * Updates an existing account.
     *
     * @param id      the ID of the parkinglot to update
     * @param request the {@link UpdateParkingLotRequest} containing updated parkinglot details
     * @return a {@link ResponseEntity} indicating the result of the operation
     */
    @PatchMapping("/id/{id}")
    public ResponseEntity<?> updateParkingLot(@PathVariable String id, @RequestBody UpdateParkingLotRequest request) {
        try {
            UUID uuid = UUID.fromString(id);
            updateUseCase.execute(uuid, new UpdateParkingLotUseCase.UpdateParameters(
                    request.name(),
                    request.location(),
                    request.capacity(),
                    request.availableSpots()
            ));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }

    /**
     * Retrieves a parkinglot by its ID.
     *
     * @param id the ID of the parkinglot to retrieve
     * @return a {@link ResponseEntity} containing the {@link ParkingLotResponse}
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getParkingLotById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id); // Validate UUID format
            ParkingLot parkingLot = findByIdUseCase.execute(uuid);
            ParkingLotResponse response = new ParkingLotResponse(
                    parkingLot.id(),
                    parkingLot.name(),
                    parkingLot.location(),
                    parkingLot.capacity(),
                    parkingLot.availableSpots()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }

    /**
     * Deletes a parkinglot by its ID.
     *
     * @param id the ID of the parkinglot to delete
     * @return a {@link ResponseEntity} indicating the result of the operation
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteParkingLot(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            deleteUseCase.execute(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }
}
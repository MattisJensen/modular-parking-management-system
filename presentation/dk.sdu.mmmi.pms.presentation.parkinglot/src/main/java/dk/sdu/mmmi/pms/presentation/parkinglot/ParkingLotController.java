package dk.sdu.mmmi.pms.presentation.parkinglot;

import dk.sdu.mmmi.pms.application.parkinglot.usecase.CreateParkingLotUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.DeleteParkingLotUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.GetParkingLotUseCase;
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
    private final UpdateParkingLotUseCase updateParkingLotUseCase;
    private final GetParkingLotUseCase getParkingLotUseCase;
    private final DeleteParkingLotUseCase deleteParkingLotUseCase;

    public ParkingLotController(CreateParkingLotUseCase createUseCase,
                                UpdateParkingLotUseCase updateParkingLotUseCase,
                                GetParkingLotUseCase getParkingLotUseCase,
                                DeleteParkingLotUseCase deleteParkingLotUseCase) {
        this.createUseCase = createUseCase;
        this.updateParkingLotUseCase = updateParkingLotUseCase;
        this.getParkingLotUseCase = getParkingLotUseCase;
        this.deleteParkingLotUseCase = deleteParkingLotUseCase;
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
            updateParkingLotUseCase.execute(uuid, new UpdateParkingLotUseCase.UpdateParameters(
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
            ParkingLot parkingLot = getParkingLotUseCase.execute(uuid);
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
            deleteParkingLotUseCase.execute(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }
}
package dk.sdu.mmmi.pms.presentation.parkingspot;

import dk.sdu.mmmi.pms.application.parkingspot.usecase.*;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.presentation.parkingspot.datatransferobject.CreateParkingSpotRequest;
import dk.sdu.mmmi.pms.presentation.parkingspot.datatransferobject.ParkingSpotResponse;
import dk.sdu.mmmi.pms.presentation.parkingspot.datatransferobject.UpdateParkingSpotRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/parking-spot")
public class ParkingSpotController {
    private final CreateParkingSpotUseCase createUseCase;
    private final DeleteParkingSpotByIdUseCase deleteUseCase;
    private final FindParkingSpotByIdUseCase findByIdUseCase;
    private final FindParkingSpotsByParkingLotIdUseCase findSpotsByParkingLotIdUseCase;
    private final UpdateParkingSpotUseCase updateUseCase;

    public ParkingSpotController(
            CreateParkingSpotUseCase createUseCase,
            DeleteParkingSpotByIdUseCase deleteUseCase,
            FindParkingSpotByIdUseCase findByIdUseCase,
            FindParkingSpotsByParkingLotIdUseCase findSpotsByParkingLotIdUseCase,
            UpdateParkingSpotUseCase updateUseCase) {
        this.createUseCase = createUseCase;
        this.deleteUseCase = deleteUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findSpotsByParkingLotIdUseCase = findSpotsByParkingLotIdUseCase;
        this.updateUseCase = updateUseCase;
    }

    @PostMapping
    public ResponseEntity<UUID> createParkingSpot(@RequestBody CreateParkingSpotRequest request) {
        UUID spotId = createUseCase.createSpot(request.parkingLotId(), request.identifier());
        ParkingSpot spot = findByIdUseCase.execute(spotId);
        return ResponseEntity.status(HttpStatus.CREATED).body(spotId);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getParkingSpotById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            ParkingSpot parkingSpot = findByIdUseCase.execute(uuid);
            ParkingSpotResponse response = new ParkingSpotResponse(
                    parkingSpot.id(),
                    parkingSpot.parkingLotId(),
                    parkingSpot.spotIdentifier()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> updateParkingSpot(
            @PathVariable String id,
            @RequestBody UpdateParkingSpotRequest request) {
        try {
            UUID uuid = UUID.fromString(id);
            updateUseCase.execute(uuid, new UpdateParkingSpotUseCase.UpdateParameters(
                    request.parkingLotId(),
                    request.spotIdentifier()
            ));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteParkingSpot(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            deleteUseCase.execute(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }

    @GetMapping("/id/parking-lot/{parkingLotId}")
    public ResponseEntity<?> getParkingSpotsByParkingLot(@PathVariable String parkingLotId) {
        try {
            UUID uuid = UUID.fromString(parkingLotId);

            List<ParkingSpot> spots = findSpotsByParkingLotIdUseCase.execute(uuid);
            List<ParkingSpotResponse> responses = spots.stream()
                    .map(spot -> new ParkingSpotResponse(
                            spot.id(),
                            spot.parkingLotId(),
                            spot.spotIdentifier()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responses);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + parkingLotId);
        }
    }
}
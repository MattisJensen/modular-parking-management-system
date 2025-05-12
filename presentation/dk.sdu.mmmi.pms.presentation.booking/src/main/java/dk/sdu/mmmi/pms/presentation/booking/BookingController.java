package dk.sdu.mmmi.pms.presentation.booking;

import dk.sdu.mmmi.pms.application.booking.usecase.*;
import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.presentation.booking.datatransferobject.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private final CreateBookingInParkingLotUseCase createInParkingLotUseCase;
    private final CreateBookingUseCase createUseCase;
    private final DeleteBookingByIdUseCase deleteUseCase;
    private final FindBookingByIdUseCase findByIdUseCase;
    private final FindAvailableParkingSpotsUseCase findAvailableSpotsUseCase;
    private final UpdateBookingUseCase updateUseCase;

    public BookingController(
            CreateBookingInParkingLotUseCase createInParkingLotUseCase,
            CreateBookingUseCase createUseCase,
            DeleteBookingByIdUseCase deleteUseCase,
            FindAvailableParkingSpotsUseCase findAvailableSpotsUseCase,
            FindBookingByIdUseCase findByIdUseCase,
            UpdateBookingUseCase updateUseCase
    ) {
        this.createInParkingLotUseCase = createInParkingLotUseCase;
        this.createUseCase = createUseCase;
        this.deleteUseCase = deleteUseCase;
        this.findAvailableSpotsUseCase = findAvailableSpotsUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.updateUseCase = updateUseCase;
    }

    @PostMapping("/parking-spot")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody CreateBookingRequest request) {
        Booking booking = createUseCase.execute(
                request.userId(),
                request.parkingSpotId(),
                request.startTime(),
                request.endTime()
        );

        BookingResponse response = new BookingResponse(
                booking.id(),
                booking.userId(),
                booking.parkingSpotId(),
                booking.startTime(),
                booking.endTime(),
                booking.bookingStatus()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/parking-lot")
    public ResponseEntity<BookingResponse> createBookingInParkingLot(@RequestBody CreateParkingLotBookingRequest request) {
        Booking booking = createInParkingLotUseCase.execute(
                request.userId(),
                request.parkingLotId(),
                request.startTime(),
                request.endTime()
        );

        BookingResponse response = new BookingResponse(
                booking.id(),
                booking.userId(),
                booking.parkingSpotId(),
                booking.startTime(),
                booking.endTime(),
                booking.bookingStatus()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable UUID id) {
        Booking booking = findByIdUseCase.execute(id);
        BookingResponse response = new BookingResponse(
                booking.id(),
                booking.userId(),
                booking.parkingSpotId(),
                booking.startTime(),
                booking.endTime(),
                booking.bookingStatus()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/parking-lot")
    public ResponseEntity<List<ParkingSpotAvailabilityResponse>> getDailyAvailability(@RequestBody ParkingSpotAvailabilityRequest request) {
        List<FindAvailableParkingSpotsUseCase.AvailableBooking> availabilities = findAvailableSpotsUseCase.execute(request.parkingSpotId(), request.availableSpotsFrom());

        List<ParkingSpotAvailabilityResponse> response = availabilities.stream()
                .map(availableBooking -> new ParkingSpotAvailabilityResponse(
                        availableBooking.parkingSpotId(),
                        availableBooking.availableFrom(),
                        availableBooking.availableUntil()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            deleteUseCase.execute(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable String id, @RequestBody UpdateBookingRequest request) {
        try {
            UUID uuid = UUID.fromString(id);
            updateUseCase.execute(uuid, new UpdateBookingUseCase.UpdateParameters(
                    request.parkingSpotId(),
                    request.startTime(),
                    request.endTime(),
                    request.bookingStatus()
            ));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }
}
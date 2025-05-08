package dk.sdu.mmmi.pms.presentation.booking;

import dk.sdu.mmmi.pms.application.booking.usecase.CreateBookingUseCase;
import dk.sdu.mmmi.pms.application.booking.usecase.DeleteBookingByIdUseCase;
import dk.sdu.mmmi.pms.application.booking.usecase.FindBookingByIdUseCase;
import dk.sdu.mmmi.pms.application.booking.usecase.UpdateBookingUseCase;
import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.presentation.booking.datatransferobject.BookingResponse;
import dk.sdu.mmmi.pms.presentation.booking.datatransferobject.CreateBookingRequest;
import dk.sdu.mmmi.pms.presentation.booking.datatransferobject.UpdateBookingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final CreateBookingUseCase createUseCase;
    private final DeleteBookingByIdUseCase deleteUseCase;
    private final FindBookingByIdUseCase findByIdUseCase;
    private final UpdateBookingUseCase updateUseCase;

    public BookingController(
            CreateBookingUseCase createUseCase,
            DeleteBookingByIdUseCase deleteUseCase,
            FindBookingByIdUseCase findByIdUseCase,
            UpdateBookingUseCase updateUseCase
    ) {
        this.createUseCase = createUseCase;
        this.deleteUseCase = deleteUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.updateUseCase = updateUseCase;
    }

    @PostMapping
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
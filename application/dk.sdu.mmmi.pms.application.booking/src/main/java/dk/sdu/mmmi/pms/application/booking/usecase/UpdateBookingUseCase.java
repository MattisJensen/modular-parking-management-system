package dk.sdu.mmmi.pms.application.booking.usecase;

import dk.sdu.mmmi.pms.application.booking.BookingRepository;
import dk.sdu.mmmi.pms.application.booking.util.BookingFormatter;
import dk.sdu.mmmi.pms.application.booking.util.BookingValidator;
import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.core.booking.BookingStatus;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotFinder;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateBookingUseCase {
    private final BookingRepository bookingRepository;
    private final BookingFormatter bookingFormatter;
    private final BookingValidator bookingValidator;
    private final FindBookingByIdUseCase findBookingByIdUseCase;
    private final ParkingSpotFinder parkingSpotFinder;

    public UpdateBookingUseCase(BookingRepository bookingRepository,
                                BookingFormatter bookingFormatter,
                                BookingValidator bookingValidator,
                                FindBookingByIdUseCase findBookingByIdUseCase,
                                ParkingSpotFinder parkingSpotFinder) {
        this.bookingRepository = bookingRepository;
        this.bookingFormatter = bookingFormatter;
        this.bookingValidator = bookingValidator;
        this.findBookingByIdUseCase = findBookingByIdUseCase;
        this.parkingSpotFinder = parkingSpotFinder;
    }

    public void execute(UUID bookingId, UpdateParameters parameters) {
        Booking existing = findBookingByIdUseCase.execute(bookingId);
        Booking updated = createUpdatedBooking(existing, parameters);

        // Format the updated booking time to the nearest 30 minutes
        updated = new Booking(
                updated.id(),
                updated.userId(),
                updated.parkingSpotId(),
                bookingFormatter.roundTo30Minutes(updated.startTime(), false),
                bookingFormatter.roundTo30Minutes(updated.endTime(), true),
                updated.bookingStatus()
        );

        validateUpdate(updated, parameters);
        bookingRepository.update(updated);
    }

    private void validateUpdate(Booking updated, UpdateParameters parameters) {
        // Validate parking spot exists if changing
        if (parameters.parkingSpotId() != null) parkingSpotFinder.execute(parameters.parkingSpotId());

        // Validate booking time
        bookingValidator.validateBookingTimeConstraints(updated.startTime(), updated.endTime());
        bookingValidator.validateNoBookingConflict(updated.parkingSpotId(), updated.startTime(), updated.endTime());
    }

    private Booking createUpdatedBooking(Booking existing, UpdateParameters parameters) {
        return new Booking(
                existing.id(),
                existing.userId(),
                parameters.parkingSpotId() != null ? parameters.parkingSpotId() : existing.parkingSpotId(),
                parameters.startTime() != null ? parameters.startTime() : existing.startTime(),
                parameters.endTime() != null ? parameters.endTime() : existing.endTime(),
                parameters.bookingStatus() != null ? parameters.bookingStatus() : existing.bookingStatus()
        );
    }

    public record UpdateParameters(
            UUID parkingSpotId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            BookingStatus bookingStatus
    ) {
    }
}

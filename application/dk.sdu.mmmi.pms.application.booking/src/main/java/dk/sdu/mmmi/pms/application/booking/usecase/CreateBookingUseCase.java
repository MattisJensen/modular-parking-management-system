package dk.sdu.mmmi.pms.application.booking.usecase;

import dk.sdu.mmmi.pms.application.booking.BookingRepository;
import dk.sdu.mmmi.pms.application.booking.util.BookingFormatter;
import dk.sdu.mmmi.pms.application.booking.util.BookingValidator;
import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.core.booking.BookingStatus;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotFinder;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateBookingUseCase {
    private final BookingRepository bookingRepository;
    private final BookingFormatter bookingFormatter;
    private final BookingValidator bookingValidator;
    private final ParkingSpotFinder parkingSpotFinder;

    public CreateBookingUseCase(BookingRepository bookingRepository,
                                BookingFormatter bookingFormatter,
                                BookingValidator bookingValidator,
                                ParkingSpotFinder parkingSpotFinder) {
        this.bookingRepository = bookingRepository;
        this.bookingFormatter = bookingFormatter;
        this.bookingValidator = bookingValidator;
        this.parkingSpotFinder = parkingSpotFinder;
    }

    public Booking execute(UUID userId, UUID parkingSpotId, LocalDateTime startTime, LocalDateTime endTime) {
        // Validate parking spot exists
        parkingSpotFinder.execute(parkingSpotId);

        // Calculate end time based on bookingType
        if(startTime == null) throw new IllegalArgumentException("Start time must not be null");
        if(endTime == null) throw new IllegalArgumentException("End time must not be null");

        // Round time to the nearest 30 minutes
        startTime = bookingFormatter.roundTo30Minutes(startTime, false);
        endTime = bookingFormatter.roundTo30Minutes(endTime, true);

        // Validate booking time
        bookingValidator.validateBookingTimeConstraints(startTime, endTime);
        bookingValidator.validateNoBookingConflict(parkingSpotId, startTime, endTime);

        UUID bookingId = UUID.randomUUID();

        Booking newBooking = new Booking(
                bookingId,
                userId,
                parkingSpotId,
                startTime,
                endTime,
                BookingStatus.RESERVED
        );

        newBooking = bookingFormatter.updateStatusBasedOnTime(newBooking);
        bookingRepository.save(newBooking);
        return newBooking;
    }
}
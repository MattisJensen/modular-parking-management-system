package dk.sdu.mmmi.pms.application.booking.usecase;

import dk.sdu.mmmi.pms.application.booking.BookingRepository;
import dk.sdu.mmmi.pms.application.booking.util.BookingFormatter;
import dk.sdu.mmmi.pms.application.booking.util.BookingValidator;
import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.core.booking.BookingStatus;
import dk.sdu.mmmi.pms.core.booking.exception.BookingTimeException;
import dk.sdu.mmmi.pms.core.parkinglot.usecase.ParkingLotFinder;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotsInLotFinder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Use case for creating a booking in a parking lot by finding the first available parking spot.
 */
public class CreateBookingInParkingLotUseCase {
    private final BookingRepository bookingRepository;
    private final BookingFormatter bookingFormatter;
    private final BookingValidator bookingValidator;
    private final ParkingLotFinder parkingLotFinder;
    private final ParkingSpotsInLotFinder parkingSpotsInLotFinder;

    public CreateBookingInParkingLotUseCase(
            BookingRepository bookingRepository,
            BookingFormatter bookingFormatter,
            BookingValidator bookingValidator,
            ParkingLotFinder parkingLotFinder,
            ParkingSpotsInLotFinder parkingSpotsInLotFinder
    ) {
        this.bookingRepository = bookingRepository;
        this.bookingFormatter = bookingFormatter;
        this.bookingValidator = bookingValidator;
        this.parkingLotFinder = parkingLotFinder;
        this.parkingSpotsInLotFinder = parkingSpotsInLotFinder;
    }

    /**
     * Executes the use case to create a booking in the specified parking lot.
     *
     * @param userId       The ID of the user making the booking
     * @param parkingLotId The ID of the parking lot to find a spot in
     * @param startTime    The start time of the booking
     * @param endTime      The end time of the booking
     * @return The created booking
     * @throws BookingTimeException If no available spots are found
     */
    public Booking execute(UUID userId, UUID parkingLotId, LocalDateTime startTime, LocalDateTime endTime) {
        // Validate parking lot exists
        parkingLotFinder.execute(parkingLotId);

        // Round time to nearest 30 minutes
        startTime = bookingFormatter.roundTo30Minutes(startTime, false);
        endTime = bookingFormatter.roundTo30Minutes(endTime, true);

        // Validate booking time constraints
        bookingValidator.validateBookingTimeConstraints(startTime, endTime);

        // Get all spots in the parking lot
        List<ParkingSpot> parkingSpots = parkingSpotsInLotFinder.execute(parkingLotId);

        // Find first available spot
        for (ParkingSpot spot : parkingSpots) {
            try {
                bookingValidator.validateNoBookingConflict(spot.id(), startTime, endTime);
                UUID bookingId = UUID.randomUUID();
                Booking newBooking = new Booking(
                        bookingId,
                        userId,
                        spot.id(),
                        startTime,
                        endTime,
                        BookingStatus.RESERVED
                );

                // Update status based on current time
                newBooking = bookingFormatter.updateStatusBasedOnTime(newBooking, LocalDateTime.now());
                bookingRepository.save(newBooking);
                return newBooking;
            } catch (BookingTimeException ignored) {}
        }

        throw new BookingTimeException("No available parking spots in parking lot " + parkingLotId + " for the requested time period");
    }
}

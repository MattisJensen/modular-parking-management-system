package dk.sdu.mmmi.pms.application.booking.usecase;

import dk.sdu.mmmi.pms.application.booking.BookingRepository;
import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpot;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotsInLotFinder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class FindAvailableParkingSpotsUseCase {
    private final ParkingSpotsInLotFinder parkingSpotsInLotFinder;
    private final BookingRepository bookingRepository;

    public FindAvailableParkingSpotsUseCase(
            ParkingSpotsInLotFinder parkingSpotsInLotFinder,
            BookingRepository bookingRepository
    ) {
        this.parkingSpotsInLotFinder = parkingSpotsInLotFinder;
        this.bookingRepository = bookingRepository;
    }

    public List<AvailableBooking> execute(UUID parkingLotId, LocalDateTime currentTime) {
        final LocalDateTime endTime = currentTime.toLocalDate().atTime(LocalTime.MAX);

        List<AvailableBooking> availableParkingSpotBookings = new ArrayList<>();
        List<ParkingSpot> allSpots = parkingSpotsInLotFinder.execute(parkingLotId);

        for (ParkingSpot parkingSpot : allSpots) {
            List<Booking> currentBookings = findCurrentBookings(parkingSpot.id(), currentTime, endTime);

            if (currentBookings.isEmpty()) {
                availableParkingSpotBookings.add(new AvailableBooking(
                        parkingSpot.id(),
                        currentTime,
                        endTime
                ));
            } else {
                // Sort bookings by start time
                if (currentBookings.size() > 1) {
                    currentBookings.sort(Comparator.comparing(Booking::startTime));
                }

                // Check for initial gap between currentTime and first booking
                if (currentTime.isBefore(currentBookings.getFirst().startTime())) {
                    availableParkingSpotBookings.add(new AvailableBooking(
                            parkingSpot.id(),
                            currentTime,
                            currentBookings.getFirst().startTime()
                    ));
                }

                // Find gaps between bookings
                for (int i = 0; i < currentBookings.size() - 1; i++) {
                    LocalDateTime gapStart = currentBookings.get(i).endTime();
                    LocalDateTime gapEnd = currentBookings.get(i + 1).startTime();

                    if (gapStart.isBefore(gapEnd)) {
                        availableParkingSpotBookings.add(new AvailableBooking(
                                parkingSpot.id(),
                                gapStart,
                                gapEnd
                        ));
                    }
                }

                // Check for final gap after last booking
                Booking lastBooking = currentBookings.getLast();
                if (lastBooking.endTime().isBefore(endTime)) {
                    availableParkingSpotBookings.add(new AvailableBooking(
                            parkingSpot.id(),
                            lastBooking.endTime(),
                            endTime
                    ));
                }
            }
        }

        return availableParkingSpotBookings;
    }

    private List<Booking> findCurrentBookings(UUID parkingSpotId, LocalDateTime currentTime, LocalDateTime endTime) {
        return bookingRepository.findBookingsForDateRange(parkingSpotId, currentTime, endTime);
    }

    public record AvailableBooking(
            UUID parkingSpotId,
            LocalDateTime availableFrom,
            LocalDateTime availableUntil
    ) {
    }
}
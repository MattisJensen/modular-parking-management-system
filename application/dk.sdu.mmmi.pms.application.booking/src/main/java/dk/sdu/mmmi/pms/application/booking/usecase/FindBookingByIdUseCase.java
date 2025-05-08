package dk.sdu.mmmi.pms.application.booking.usecase;

import dk.sdu.mmmi.pms.application.booking.BookingRepository;
import dk.sdu.mmmi.pms.application.booking.util.BookingFormatter;
import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.core.booking.exception.BookingNotFoundException;

import java.util.UUID;

public class FindBookingByIdUseCase {
    private final BookingRepository repository;
    private final BookingFormatter bookingFormatter;

    public FindBookingByIdUseCase(BookingRepository repository, BookingFormatter bookingFormatter) {
        this.repository = repository;
        this.bookingFormatter = bookingFormatter;
    }

    public Booking execute(UUID id) {
        Booking booking = repository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));

        Booking updatedBooking = bookingFormatter.updateStatusBasedOnTime(booking);
        if (updatedBooking.bookingStatus() != booking.bookingStatus()) repository.update(updatedBooking);

        return updatedBooking;
    }
}
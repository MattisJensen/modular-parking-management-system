package dk.sdu.mmmi.pms.application.booking;

import dk.sdu.mmmi.pms.core.booking.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository {
    void save(Booking booking);
    void update(Booking booking);
    Optional<Booking> findById(UUID id);
    List<Booking> findByUserId(UUID userId);
    List<Booking> findBookingsForDateRange(UUID parkingSpotId, LocalDateTime rangeStart, LocalDateTime rangeEnd);
    List<Booking> findOverlappingBookings(UUID parkingSpotId, LocalDateTime start, LocalDateTime end);
    void deleteById(UUID id);
}

package dk.sdu.mmmi.pms.infrastructure.booking.persistence;

import dk.sdu.mmmi.pms.core.booking.Booking;
import org.springframework.stereotype.Component;

/**
 * Maps between the core model {@link Booking} and the persistence model {@link BookingJpaEntity}.
 */
@Component
public class BookingMapper {

    /**
     * Converts a core model {@link Booking} to a persistence model {@link BookingJpaEntity}.
     *
     * @param core the {@link Booking} to be converted
     * @return the corresponding {@link BookingJpaEntity}
     */
    public BookingJpaEntity toJpaEntity(Booking core) {
        return new BookingJpaEntity(
                core.id(),
                core.userId(),
                core.parkingSpotId(),
                core.startTime(),
                core.endTime(),
                core.bookingStatus()
        );
    }

    /**
     * Converts a persistence model {@link BookingJpaEntity} to a core model {@link Booking}.
     *
     * @param jpa the {@link BookingJpaEntity} to be converted
     * @return the corresponding {@link Booking}
     */
    public Booking toCore(BookingJpaEntity jpa) {
        return new Booking(
                jpa.getId(),
                jpa.getUserId(),
                jpa.getParkingSpotId(),
                jpa.getStartTime(),
                jpa.getEndTime(),
                jpa.getStatus()
        );
    }
}
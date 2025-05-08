package dk.sdu.mmmi.pms.application.booking.util;

import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.core.booking.BookingStatus;

import java.time.LocalDateTime;

public class BookingFormatter {
    /**
     * Update the booking status based on the current time.
     *
     * @param booking The booking to update
     * @param timeToCheck The time to check against
     * @return The updated booking with the new status
     */
    public Booking updateStatusBasedOnTime(Booking booking, LocalDateTime timeToCheck) {
        BookingStatus newStatus = booking.bookingStatus();

        if (booking.bookingStatus() == BookingStatus.RESERVED && timeToCheck.isAfter(booking.startTime())) {
            newStatus = BookingStatus.ACTIVE;
        }

        if (booking.bookingStatus() == BookingStatus.ACTIVE && timeToCheck.isAfter(booking.endTime())) {
            newStatus = BookingStatus.COMPLETED;
        }

        // Only create new object if bookingStatus changed
        if (newStatus != booking.bookingStatus()) {
            return new Booking(
                    booking.id(),
                    booking.userId(),
                    booking.parkingSpotId(),
                    booking.startTime(),
                    booking.endTime(),
                    newStatus
            );
        }

        return booking;
    }

    /**
     * Round the given {@link LocalDateTime} to the nearest 30 minutes above or below.
     *
     * @param dateTime The {@link LocalDateTime} to round
     * @param roundUp If true, round up to the next 30 minutes, otherwise round down
     * @return The rounded {@link LocalDateTime}
     */
    public LocalDateTime roundTo30Minutes(LocalDateTime dateTime, boolean roundUp) {
        int minutes = dateTime.getMinute();
        int roundedMinutes;

        // Round to the nearest 30 minutes
        if (roundUp) {
            roundedMinutes = ((minutes + 30) / 30) * 30;
        } else {
            roundedMinutes = (minutes / 30) * 30;
        }

        // Handle rollover when minutes would be 60
        if (roundedMinutes == 60) {
            return dateTime.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        }

        return dateTime.withMinute(roundedMinutes).withSecond(0).withNano(0);
    }
}

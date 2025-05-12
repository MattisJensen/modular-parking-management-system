package dk.sdu.mmmi.pms.application.booking.util;

import dk.sdu.mmmi.pms.application.booking.BookingRepository;
import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.core.booking.exception.BookingTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BookingValidator {
    private final BookingRepository bookingRepository;

    public BookingValidator(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Validate the booking time constraints.
     *
     * @param startTime The start time of the booking
     * @param endTime   The end time of the booking
     * @throws BookingTimeException if the booking time is invalid
     */
    public void validateBookingTimeConstraints(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime maxBookingAhead = now.plusWeeks(2);
        LocalDateTime maxBookingTime = startTime.plusHours(48);

        if (startTime.isAfter(endTime)) {
            throw new BookingTimeException("Start time must be before end time");
        }

        if (endTime.minusMinutes(30).isBefore(startTime)) {
            throw new BookingTimeException("Booking time must be at least 30 minutes");
        }

        if (startTime.isBefore(now) || endTime.isBefore(now)) {
            throw new BookingTimeException("Booking time must be in the future");
        }

        if (startTime.isAfter(maxBookingAhead) || endTime.isAfter(maxBookingAhead)) {
            throw new BookingTimeException("Booking time must be within the next 2 weeks");
        }

        if (endTime.isAfter(maxBookingTime)) {
            throw new BookingTimeException("Booking time cannot exceed 48 hours");
        }
    }

    /**
     * Validate that there are no conflicting bookings for the given parking spot and time range.
     *
     * @param parkingSpotId The ID of the parking spot
     * @param startTime     The start time of the booking
     * @param endTime       The end time of the booking
     * @throws BookingTimeException if there is a conflict with existing bookings
     */
    public void validateNoBookingConflict(UUID parkingSpotId, LocalDateTime startTime, LocalDateTime endTime) {
        final LocalDateTime rangeStart = startTime.toLocalDate().atStartOfDay();
        final LocalDateTime rangeEnd = endTime.toLocalDate().atTime(LocalTime.MAX);

        // Fetch existing bookings for the given parking spot and time range
        List<Booking> existingBookings = bookingRepository.findBookingsForDateRange(parkingSpotId, rangeStart, rangeEnd);

        // Check if there is a conflict
        boolean hasConflict = existingBookings.stream().anyMatch(b -> b.startTime().isBefore(endTime) && b.endTime().isAfter(startTime));
        if (hasConflict) {
            String message = generateAvailableTimeSlotMessage(existingBookings, startTime, endTime);
            throw new BookingTimeException(message);
        }
    }

    /**
     * Generate a message with available time slots based on existing bookings.
     *
     * @param existingBookings List of existing bookings
     * @param startTime        The start time of the booking
     * @param endTime          The end time of the booking
     * @return A message with available time slots
     */
    private String generateAvailableTimeSlotMessage(List<Booking> existingBookings, LocalDateTime startTime, LocalDateTime endTime) {
        // Group bookings by date
        Map<LocalDate, List<Booking>> bookingsByDate = new HashMap<>();
        for (Booking b : existingBookings) {
            // Get the start and end date of the booking
            LocalDate startDate = b.startTime().toLocalDate();
            LocalDate endDate = b.endTime().toLocalDate();
            startDate.datesUntil(endDate.plusDays(1)).forEach(date ->
                    bookingsByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(b)
            );
        }

        // Process each day in requested range
        StringBuilder message = new StringBuilder("Available timeslots: ");

        startTime.toLocalDate().datesUntil(endTime.toLocalDate().plusDays(1))
                .forEach(date -> {
                    List<Booking> dayBookings = bookingsByDate.getOrDefault(date, List.of());
                    List<TimeSlot> freeSlots = calculateDailySlots(
                            date.atStartOfDay(),
                            date.atTime(LocalTime.MAX),
                            dayBookings
                    );

                    message.append(formatDailySlots(date, freeSlots));
                });

        return message.toString();
    }

    /**
     * Calculate available time slots for a given day based on existing bookings.
     *
     * @param dayStart Start of the day
     * @param dayEnd   End of the day
     * @param bookings List of existing bookings for the day
     * @return List of available time slots
     */
    private List<TimeSlot> calculateDailySlots(LocalDateTime dayStart, LocalDateTime dayEnd, List<Booking> bookings) {
        List<TimeSlot> slots = new ArrayList<>();
        LocalDateTime current = dayStart;

        for (Booking b : bookings.stream()
                .sorted(Comparator.comparing(Booking::startTime))
                .toList()) {
            if (current.isBefore(b.startTime())) {
                slots.add(new TimeSlot(current, b.startTime()));
            }
            current = current.isBefore(b.endTime()) ? b.endTime() : current;
        }

        if (current.isBefore(dayEnd)) {
            slots.add(new TimeSlot(current, dayEnd));
        }

        return slots;
    }

    /**
     * Format the available time slots for a given day.
     *
     * @param date  The date for which to format the slots
     * @param slots List of available time slots
     * @return Formatted string of available time slots
     */
    private String formatDailySlots(LocalDate date, List<TimeSlot> slots) {
        if (slots.isEmpty()) return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String timeSlots = slots.stream()
                // formatting time slots
                .map(slot -> slot.start().format(formatter) + "-" + slot.end().format(formatter))
                // joining time slots with comma
                .collect(Collectors.joining(", "));
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " [" + timeSlots + "], ";
    }

    private record TimeSlot(LocalDateTime start, LocalDateTime end) {
    }
}

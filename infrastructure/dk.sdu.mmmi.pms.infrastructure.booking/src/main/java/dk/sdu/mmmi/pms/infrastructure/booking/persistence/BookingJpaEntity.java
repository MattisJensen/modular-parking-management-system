package dk.sdu.mmmi.pms.infrastructure.booking.persistence;

import dk.sdu.mmmi.pms.core.booking.BookingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity representing an booking in the database.
 * This class is mapped to the "bookings" table and contains fields for booking details
 * such as ID, name, email, password and role.
 */
@Entity
@Table(name = "bookings")
public class BookingJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID parkingSpotId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    public BookingJpaEntity() {}

    public BookingJpaEntity(UUID id, UUID userId, UUID parkingSpotId, LocalDateTime startTime, LocalDateTime endTime, BookingStatus status) {
        this.id = id;
        this.userId = userId;
        this.parkingSpotId = parkingSpotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters
    public UUID getId() {return id;}
    public UUID getUserId() {return userId;}
    public UUID getParkingSpotId() {return parkingSpotId;}
    public LocalDateTime getStartTime() {return startTime;}
    public LocalDateTime getEndTime() {return endTime;}
    public BookingStatus getStatus() {return status;}
}
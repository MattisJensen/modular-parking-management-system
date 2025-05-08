package dk.sdu.mmmi.pms.infrastructure.booking.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the {@link BookingJpaEntity}.
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * and custom query methods for the {@link BookingJpaEntity}.
 */
@Repository
public interface BookingJpaRepository extends JpaRepository<BookingJpaEntity, UUID> {
    List<BookingJpaEntity> findByUserId(UUID userId);

    @Query("SELECT b FROM BookingJpaEntity b " +
            "WHERE b.parkingSpotId = :parkingSpotId " +
            "AND ( " +
            "   (b.startTime < :endTime AND b.endTime > :startTime) " + // Overlapping
            "OR (b.startTime = :startTime AND b.endTime = :endTime) " + // Exact match
            "OR (b.startTime <= :startTime AND b.endTime >= :endTime) " + // New booking is fully contained
            "OR (b.startTime >= :startTime AND b.endTime <= :endTime) " + // Existing booking is fully contained
            ")")
    List<BookingJpaEntity> findOverlappingBookings(
            @Param("parkingSpotId") UUID parkingSpotId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    @Query("SELECT b FROM BookingJpaEntity b " +
            "WHERE b.parkingSpotId = :parkingSpotId " +
            "AND b.startTime <= :rangeEnd " +
            "AND b.endTime >= :rangeStart")
    List<BookingJpaEntity> findByParkingSpotIdAndTimeRange(
            @Param("parkingSpotId") UUID parkingSpotId,
            @Param("rangeStart") LocalDateTime rangeStart,
            @Param("rangeEnd") LocalDateTime rangeEnd
    );
}
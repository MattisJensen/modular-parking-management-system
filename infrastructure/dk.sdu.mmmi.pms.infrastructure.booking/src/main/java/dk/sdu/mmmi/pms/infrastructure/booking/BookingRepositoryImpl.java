package dk.sdu.mmmi.pms.infrastructure.booking;

import dk.sdu.mmmi.pms.application.booking.BookingRepository;
import dk.sdu.mmmi.pms.core.booking.Booking;
import dk.sdu.mmmi.pms.infrastructure.booking.persistence.BookingJpaEntity;
import dk.sdu.mmmi.pms.infrastructure.booking.persistence.BookingJpaRepository;
import dk.sdu.mmmi.pms.infrastructure.booking.persistence.BookingMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the {@link BookingRepository} interface.
 * This class uses {@link BookingJpaRepository} to perform CRUD operations
 * and {@link BookingMapper} to map between domain and persistence models.
 */
@Repository
public class BookingRepositoryImpl implements BookingRepository {
    private final BookingJpaRepository springDataRepo;
    private final BookingMapper mapper;

    public BookingRepositoryImpl(BookingJpaRepository springDataRepo, BookingMapper mapper) {
        this.springDataRepo = springDataRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(Booking booking) {
        BookingJpaEntity jpaEntity = mapper.toJpaEntity(booking);
        springDataRepo.save(jpaEntity);
    }

    @Override
    public void update(Booking booking) {
        BookingJpaEntity jpaEntity = mapper.toJpaEntity(booking);
        springDataRepo.save(jpaEntity);
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return springDataRepo.findById(id).map(mapper::toCore);
    }

    @Override
    public List<Booking> findByUserId(UUID userId) {
        return springDataRepo.findByUserId(userId)
                .stream()
                .map(mapper::toCore)
                .toList();
    }

    @Override
    public List<Booking> findBookingsForDateRange(UUID parkingSpotId, LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        return springDataRepo.findByParkingSpotIdAndTimeRange(parkingSpotId, rangeStart, rangeEnd)
                .stream()
                .map(mapper::toCore)
                .toList();
    }

    @Override
    public List<Booking> findOverlappingBookings(UUID parkingSpotId, LocalDateTime start, LocalDateTime end) {
        return springDataRepo.findOverlappingBookings(parkingSpotId, start, end)
                .stream()
                .map(mapper::toCore)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepo.deleteById(id);
    }
}
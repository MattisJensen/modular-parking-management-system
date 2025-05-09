package dk.sdu.mmmi.pms.infrastructure.booking.config;

import dk.sdu.mmmi.pms.application.booking.BookingRepository;
import dk.sdu.mmmi.pms.application.booking.usecase.*;
import dk.sdu.mmmi.pms.application.booking.util.BookingFormatter;
import dk.sdu.mmmi.pms.application.booking.util.BookingValidator;
import dk.sdu.mmmi.pms.core.parkinglot.usecase.ParkingLotFinder;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotFinder;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotsInLotFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for the booking infrastructure module.
 * This class is annotated with {@link Configuration} to indicate that it provides
 * Spring configuration, {@link EnableJpaRepositories} to enable JPA repositories,
 * and {@link ComponentScan} to scan for Spring components within its module.
 */
@Configuration
@EnableJpaRepositories(basePackages = "dk.sdu.mmmi.pms.infrastructure.booking")
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.infrastructure.booking")
public class BookingConfigInfrastructure {
    @Bean
    public CreateBookingInParkingLotUseCase createBookingInParkingLotUseCase(
            BookingRepository bookingRepository,
            BookingFormatter bookingFormatter,
            BookingValidator bookingValidator,
            ParkingLotFinder parkingLotFinder,
            ParkingSpotsInLotFinder parkingSpotsInLotFinder
    ) {
        return new CreateBookingInParkingLotUseCase(
                bookingRepository,
                bookingFormatter,
                bookingValidator,
                parkingLotFinder,
                parkingSpotsInLotFinder
        );
    }

    @Bean
    public CreateBookingUseCase createBookingUseCase(BookingRepository bookingRepository,
                                                     BookingFormatter bookingFormatter,
                                                     BookingValidator bookingValidator,
                                                     ParkingSpotFinder parkingSpotFinder) {
        return new CreateBookingUseCase(bookingRepository, bookingFormatter, bookingValidator, parkingSpotFinder);
    }

    @Bean
    public DeleteBookingByIdUseCase deleteBookingByIdUseCase(BookingRepository bookingRepository,
                                                             FindBookingByIdUseCase findByIdUseCase) {
        return new DeleteBookingByIdUseCase(bookingRepository, findByIdUseCase);
    }

    @Bean
    public FindBookingByIdUseCase findBookingByIdUseCase(BookingRepository bookingRepository,
                                                         BookingFormatter bookingFormatter) {
        return new FindBookingByIdUseCase(bookingRepository, bookingFormatter);
    }

    @Bean
    UpdateBookingUseCase updateBookingUseCase(BookingRepository bookingRepository,
                                              BookingFormatter bookingFormatter,
                                              BookingValidator bookingValidator,
                                              FindBookingByIdUseCase findBookingByIdUseCase,
                                              ParkingSpotFinder parkingSpotFinder) {
        return new UpdateBookingUseCase(bookingRepository, bookingFormatter, bookingValidator, findBookingByIdUseCase, parkingSpotFinder);
    }

    @Bean
    public BookingFormatter bookingFormatter() {
        return new BookingFormatter();
    }

    @Bean
    public BookingValidator bookingValidator(BookingRepository bookingRepository) {
        return new BookingValidator(bookingRepository);
    }
}
package dk.sdu.mmmi.pms.infrastructure.parkingspot.config;

import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.application.parkingspot.usecase.*;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLotFinder;
import dk.sdu.mmmi.pms.core.parkingspot.ParkingSpotDeleter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for the parkingspot infrastructure module.
 * This class is annotated with {@link Configuration} to indicate that it provides
 * Spring configuration, {@link EnableJpaRepositories} to enable JPA repositories,
 * and {@link ComponentScan} to scan for Spring components within its module.
 */
@Configuration
@EnableJpaRepositories(basePackages = "dk.sdu.mmmi.pms.infrastructure.parkingspot")
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.infrastructure.parkingspot")
public class ParkingSpotConfigInfrastructure {
    @Bean
    public CreateParkingSpotUseCase createParkingSpotUseCase(ParkingSpotRepository repository, ParkingLotFinder findParkingLotByIdUseCase) {
        return new CreateParkingSpotUseCase(repository, findParkingLotByIdUseCase);
    }

    @Bean
    public DeleteAllParkingSpotsByParkingLotIdUseCase deleteAllParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository) {
        return new DeleteAllParkingSpotsByParkingLotIdUseCase(repository);
    }

    @Bean
    public DeleteParkingSpotByIdUseCase deleteParkingSpotUseCase(ParkingSpotRepository repository) {
        return new DeleteParkingSpotByIdUseCase(repository);
    }

    @Bean
    public FindAvailableParkingSpotsByParkingLotIdUseCase findAvailableParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository, ParkingLotFinder findParkingLotByIdUseCase) {
        return new FindAvailableParkingSpotsByParkingLotIdUseCase(repository, findParkingLotByIdUseCase);
    }

    @Bean
    public FindParkingSpotByIdUseCase getParkingSpotUseCase(ParkingSpotRepository repository) {
        return new FindParkingSpotByIdUseCase(repository);
    }

    @Bean
    public FindParkingSpotsByParkingLotIdUseCase findParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository, ParkingLotFinder findParkingLotByIdUseCase) {
        return new FindParkingSpotsByParkingLotIdUseCase(repository, findParkingLotByIdUseCase);
    }

    @Bean
    public UpdateParkingSpotUseCase updateParkingSpotUseCase(ParkingSpotRepository repository) {
        return new UpdateParkingSpotUseCase(repository);
    }
}
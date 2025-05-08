package dk.sdu.mmmi.pms.infrastructure.parkinglot.config;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.CreateParkingLotUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.DeleteParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.FindParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.UpdateParkingLotUseCase;
import dk.sdu.mmmi.pms.core.parkingspot.usecase.ParkingSpotDeleter;
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
@EnableJpaRepositories(basePackages = "dk.sdu.mmmi.pms.infrastructure.parkinglot")
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.infrastructure.parkinglot")
public class ParkingLotConfigInfrastructure {
    @Bean
    public CreateParkingLotUseCase createParkingLotUseCase(ParkingLotRepository repository) {
        return new CreateParkingLotUseCase(repository);
    }

    @Bean
    public DeleteParkingLotByIdUseCase deleteParkingLotUseCase(ParkingLotRepository repository, ParkingSpotDeleter deleteAllParkingLotByIdUseCase) {
        return new DeleteParkingLotByIdUseCase(repository, deleteAllParkingLotByIdUseCase);
    }

    @Bean
    public FindParkingLotByIdUseCase getParkingLotUseCase(ParkingLotRepository repository) {
        return new FindParkingLotByIdUseCase(repository);
    }

    @Bean
    public UpdateParkingLotUseCase updateParkingLotUseCase(ParkingLotRepository repository) {
        return new UpdateParkingLotUseCase(repository);
    }
}
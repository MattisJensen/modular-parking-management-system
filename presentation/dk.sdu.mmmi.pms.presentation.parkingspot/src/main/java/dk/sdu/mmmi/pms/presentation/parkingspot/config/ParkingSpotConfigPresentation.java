package dk.sdu.mmmi.pms.presentation.parkingspot.config;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.FindParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.application.parkingspot.usecase.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.presentation.parkingspot")
public class ParkingSpotConfigPresentation {
    @Bean
    public CreateParkingSpotUseCase createParkingSpotUseCase(ParkingSpotRepository repository, FindParkingLotByIdUseCase findParkingLotByIdUseCase) {
        return new CreateParkingSpotUseCase(repository, findParkingLotByIdUseCase);
    }

    @Bean
    public DeleteParkingSpotByIdUseCase deleteParkingSpotUseCase(ParkingSpotRepository repository) {
        return new DeleteParkingSpotByIdUseCase(repository);
    }

    @Bean
    public FindAvailableParkingSpotsByParkingLotIdUseCase findAvailableParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository, FindParkingLotByIdUseCase findParkingLotByIdUseCase) {
        return new FindAvailableParkingSpotsByParkingLotIdUseCase(repository, findParkingLotByIdUseCase);
    }

    @Bean
    public FindParkingSpotByIdUseCase getParkingSpotUseCase(ParkingSpotRepository repository) {
        return new FindParkingSpotByIdUseCase(repository);
    }

    @Bean
    public FindParkingSpotsByParkingLotIdUseCase findParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository, FindParkingLotByIdUseCase findParkingLotByIdUseCase) {
        return new FindParkingSpotsByParkingLotIdUseCase(repository, findParkingLotByIdUseCase);
    }

    @Bean
    public UpdateParkingSpotUseCase updateParkingSpotUseCase(ParkingSpotRepository repository) {
        return new UpdateParkingSpotUseCase(repository);
    }

    @Bean
    @ConditionalOnMissingBean
    public FindParkingLotByIdUseCase findParkingLotByIdUseCase(ParkingLotRepository repository) {
        return new FindParkingLotByIdUseCase(repository);
    }
}
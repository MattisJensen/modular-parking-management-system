package dk.sdu.mmmi.pms.presentation.parkinglot.config;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.CreateParkingLotUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.DeleteParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.FindParkingLotByIdUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.UpdateParkingLotUseCase;
import dk.sdu.mmmi.pms.application.parkingspot.ParkingSpotRepository;
import dk.sdu.mmmi.pms.application.parkingspot.usecase.DeleteAllParkingSpotsByParkingLotIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.presentation.parkinglot")
public class ParkingLotConfigPresentation {
    @Bean
    public CreateParkingLotUseCase createParkingLotUseCase(ParkingLotRepository repository) {
        return new CreateParkingLotUseCase(repository);
    }

    @Bean
    public DeleteParkingLotByIdUseCase deleteParkingLotUseCase(ParkingLotRepository repository, DeleteAllParkingSpotsByParkingLotIdUseCase deleteAllParkingLotByIdUseCase) {
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

    @Bean
    @ConditionalOnMissingBean(ParkingLotRepository.class)
    public DeleteAllParkingSpotsByParkingLotIdUseCase deleteAllParkingSpotsByParkingLotIdUseCase(ParkingSpotRepository repository) {
        return new DeleteAllParkingSpotsByParkingLotIdUseCase(repository);
    }
}
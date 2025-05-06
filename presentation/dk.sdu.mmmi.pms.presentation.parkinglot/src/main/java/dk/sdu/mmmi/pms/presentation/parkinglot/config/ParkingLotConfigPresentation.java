package dk.sdu.mmmi.pms.presentation.parkinglot.config;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.CreateParkingLotUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.DeleteParkingLotUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.GetParkingLotUseCase;
import dk.sdu.mmmi.pms.application.parkinglot.usecase.UpdateParkingLotUseCase;
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
    public GetParkingLotUseCase getParkingLotUseCase(ParkingLotRepository repository) {
        return new GetParkingLotUseCase(repository);
    }

    @Bean
    public UpdateParkingLotUseCase updateParkingLotUseCase(ParkingLotRepository repository, GetParkingLotUseCase getUseCase) {
        return new UpdateParkingLotUseCase(repository, getUseCase);
    }

    @Bean
    public DeleteParkingLotUseCase deleteParkingLotUseCase(ParkingLotRepository repository) {
        return new DeleteParkingLotUseCase(repository);
    }
}
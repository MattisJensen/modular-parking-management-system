package dk.sdu.mmmi.pms.presentation.parkinglot.config;

import dk.sdu.mmmi.pms.application.parkinglot.usecase.CreateParkingLotUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.presentation.parkinglot")
public class ParkingLotConfigPresentation {
    @Bean
    public CreateParkingLotUseCase createParkingLotUseCase(
            dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository repository
    ) {
        return new CreateParkingLotUseCase(repository);
    }
}
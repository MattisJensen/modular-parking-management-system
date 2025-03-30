package dk.sdu.mmmi.pms.presentation.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({
//        "dk.sdu.mmmi.pms.presentation.account",
//        "dk.sdu.mmmi.pms.infrastructure.account",
//        "dk.sdu.mmmi.pms.infrastructure.database",
//        "dk.sdu.mmmi.pms.infrastructure.security"
//})
public class ParkingManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingManagementSystemApplication.class, args);
    }
}
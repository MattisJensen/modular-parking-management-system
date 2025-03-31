package dk.sdu.mmmi.pms.presentation.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "dk.sdu.mmmi.pms.presentation.account")
//@ComponentScan({
////        "dk.sdu.mmmi.pms.infrastructure.account",
////        "dk.sdu.mmmi.pms.infrastructure.database",
////        "dk.sdu.mmmi.pms.infrastructure.security",
//        "dk.sdu.mmmi.pms.presentation.account",
//})
public class ParkingManagementSystemApplication {
    public static void main(String[] args) {
        System.out.println("Starting Parking Management System Application");
        SpringApplication.run(ParkingManagementSystemApplication.class, args);
    }
}
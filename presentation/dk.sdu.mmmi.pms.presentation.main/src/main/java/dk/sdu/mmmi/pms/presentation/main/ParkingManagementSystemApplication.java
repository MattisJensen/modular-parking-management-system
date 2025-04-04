package dk.sdu.mmmi.pms.presentation.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@ComponentScan({
//        "dk.sdu.mmmi.pms.infrastructure.account",
//        "dk.sdu.mmmi.pms.infrastructure.account.jpa",
        "dk.sdu.mmmi.pms.infrastructure.database",
        "dk.sdu.mmmi.pms.infrastructure.security",
        "dk.sdu.mmmi.pms.presentation.account",
//        "dk.sdu.mmmi.pms.presentation.main",
})
public class ParkingManagementSystemApplication {
    public static void main(String[] args) {
        System.out.println("Starting Parking Management System Application");
        ConfigurableApplicationContext context = SpringApplication.run(ParkingManagementSystemApplication.class, args);

        printPmsSpecificBeans(context);
    }

    public static void printPmsSpecificBeans(ConfigurableApplicationContext context) {
        System.out.println("\n=== Beans of PMS Modules ===");
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        int pmsBeanCount = 0;

        for (String beanName : beanNames) {
            try {
                Class<?> type = context.getType(beanName);
                if (type != null && type.getName().startsWith("dk.sdu.mmmi.pms")) {
                    System.out.println(beanName + " - " + type.getName());
                    pmsBeanCount++;
                }
            } catch (Exception e) {
                System.out.println("Error getting type for bean: " + beanName);
            }
        }

        System.out.println("Total PMS specific beans: " + pmsBeanCount);
    }
}
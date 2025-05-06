package dk.sdu.mmmi.pms.infrastructure.parkingspot.config;

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
}
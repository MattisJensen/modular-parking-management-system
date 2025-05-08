package dk.sdu.mmmi.pms.presentation.booking.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the booking presentation module.
 * This class is annotated with {@link Configuration} to indicate that it provides
 * Spring configuration and {@link ComponentScan} to scan for Spring components
 * within its module.
 */
@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.presentation.booking")
public class BookingConfigPresentation {
}



package dk.sdu.mmmi.pms.infrastructure.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the security infrastructure module.
 * This class is annotated with {@link Configuration} to indicate that it provides
 * Spring configuration and {@link ComponentScan} to scan for Spring components
 * within its module.
 */
@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.infrastructure.security")
public class SecurityConfigInfrastructure {
}

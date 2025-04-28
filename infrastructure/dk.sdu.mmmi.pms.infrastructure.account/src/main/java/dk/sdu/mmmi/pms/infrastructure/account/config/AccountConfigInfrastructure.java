package dk.sdu.mmmi.pms.infrastructure.account.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "dk.sdu.mmmi.pms.infrastructure.account")
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.infrastructure.account")
public class AccountConfigInfrastructure {
}

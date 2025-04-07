package dk.sdu.mmmi.pms.infrastructure.database;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean emf = builder
                .dataSource(dataSource)
                .packages("dk.sdu.mmmi.pms.infrastructure")
                .build();

        emf.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);

        return emf;
    }
}
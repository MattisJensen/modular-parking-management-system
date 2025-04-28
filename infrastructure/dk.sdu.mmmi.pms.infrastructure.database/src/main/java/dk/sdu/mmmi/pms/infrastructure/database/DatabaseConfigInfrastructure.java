package dk.sdu.mmmi.pms.infrastructure.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.infrastructure.database")
@PropertySource("classpath:application.properties")
public class DatabaseConfigInfrastructure {

    /**
     * Configures the DataSource bean, which provides the database connection.
     *
     * @param env the Spring Environment object to access properties from the application.properties file.
     * @return a configured DataSource object.
     */
    @Bean
    public DataSource dataSource(Environment env) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    /**
     * Configures the LocalContainerEntityManagerFactoryBean, which manages the JPA entity manager.
     *
     * @param dataSource the DataSource bean used for database connections.
     * @param env the Spring Environment object to access properties from the properties file.
     * @return a configured LocalContainerEntityManagerFactoryBean object.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        // Set the data source and package to scan for JPA entities
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan("dk.sdu.mmmi.pms.infrastructure");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Set JPA properties
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        props.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));

        entityManager.setJpaProperties(props);
        entityManager.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);

        return entityManager;
    }

    /**
     * Configures the PlatformTransactionManager bean, which manages transactions.
     *
     * @param emf the LocalContainerEntityManagerFactoryBean used to create the EntityManagerFactory.
     * @return a configured PlatformTransactionManager object.
     */
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }
}
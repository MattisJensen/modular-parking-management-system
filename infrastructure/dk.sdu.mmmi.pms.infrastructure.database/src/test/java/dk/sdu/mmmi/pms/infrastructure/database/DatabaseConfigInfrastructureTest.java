package dk.sdu.mmmi.pms.infrastructure.database;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConfigInfrastructureTest {

    @Test
    void verifyBeanCreation() throws SQLException {
        // Create application context
        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfigInfrastructure.class);

        // Verify DataSource bean
        DataSource dataSource = context.getBean(DataSource.class);
        assertNotNull(dataSource);
        assertNotNull(dataSource.getConnection()); // Verify connection

        // Verify EntityManagerFactory bean
        LocalContainerEntityManagerFactoryBean entityManager = context.getBean(LocalContainerEntityManagerFactoryBean.class);
        assertNotNull(entityManager);

        // Verify TransactionManager bean
        PlatformTransactionManager txManager = context.getBean(PlatformTransactionManager.class);
        assertNotNull(txManager);
    }

    @Test
    void verifyPropertyInjection() {
        // Create context with test properties
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().addActiveProfile("test");
        context.register(DatabaseConfigInfrastructure.class);
        context.refresh();

        // Verify properties
        Environment env = context.getEnvironment();
        assertEquals("org.postgresql.Driver", env.getProperty("spring.datasource.driver-class-name"));
        assertEquals("update", env.getProperty("spring.jpa.hibernate.ddl-auto"));
    }
}
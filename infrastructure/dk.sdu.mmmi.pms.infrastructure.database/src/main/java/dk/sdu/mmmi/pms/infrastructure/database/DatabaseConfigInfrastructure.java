package dk.sdu.mmmi.pms.infrastructure.database;

import jakarta.persistence.EntityManagerFactory;
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

//import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.infrastructure.database")
@PropertySource("classpath:application.properties")
//@EnableTransactionManagement
public class DatabaseConfigInfrastructure {
    /**
     * Creates a DataSource using properties from application.properties.
     */
    @Bean
    public DataSource dataSource(Environment env) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        ds.setUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        return ds;
    }

    /**
     * Creates the entity manager factory bean and sets JPA properties.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("dk.sdu.mmmi.pms.infrastructure.account");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        props.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        factoryBean.setJpaProperties(props);

        return factoryBean;
    }


    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }
    /**
     * Creates the transactional manager bean for JPA.
     */
//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder, DataSource dataSource) {
//
//        LocalContainerEntityManagerFactoryBean emf = builder
//                .dataSource(dataSource)
//                .packages("dk.sdu.mmmi.pms.infrastructure")
//                .build();
//
//        emf.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);
//
//        return emf;
//    }

}
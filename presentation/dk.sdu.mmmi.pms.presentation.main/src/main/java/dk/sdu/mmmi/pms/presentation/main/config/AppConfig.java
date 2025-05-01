package dk.sdu.mmmi.pms.presentation.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Configuration class for the main presentation module.
 * This class is annotated with {@link Configuration} to indicate that it provides
 * Spring configuration and {@link ComponentScan} to scan for Spring components
 * within its module.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.presentation.main")
public class AppConfig implements WebMvcConfigurer {

    /**
     * Configures the list of {@link HttpMessageConverter} instances.
     * Adds a custom {@link MappingJackson2HttpMessageConverter} for JSON processing.
     *
     * @param converters the list of {@link HttpMessageConverter} to configure
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
    }

    /**
     * Creates a {@link MappingJackson2HttpMessageConverter} bean for JSON message conversion.
     *
     * @return the {@link MappingJackson2HttpMessageConverter} instance
     */
    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}
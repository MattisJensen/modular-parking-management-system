package dk.sdu.mmmi.pms.presentation.main.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.presentation.main")
public class AppConfig {
}
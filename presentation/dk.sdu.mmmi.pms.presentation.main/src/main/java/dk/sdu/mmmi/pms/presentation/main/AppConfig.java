package dk.sdu.mmmi.pms.presentation.main;

import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@ComponentScan(basePackages = "dk.sdu.mmmi.pms")
@EnableWebMvc
//@EnableJpaRepositories(basePackages = "dk.sdu.mmmi.pms.infrastructure")
public class AppConfig implements WebMvcConfigurer {

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new MappingJackson2HttpMessageConverter());
//    }
}
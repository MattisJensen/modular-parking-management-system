package dk.sdu.mmmi.pms.infrastructure.security.config;

import dk.sdu.mmmi.pms.infrastructure.security.authentication.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for the security infrastructure module.
 * This class is annotated with {@link Configuration} to indicate that it provides
 * Spring configuration and {@link ComponentScan} to scan for Spring components
 * within its module.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.infrastructure.security")
public class SecurityConfigInfrastructure {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor for {@link SecurityConfigInfrastructure}.
     *
     * @param jwtAuthenticationFilter the {@link JwtAuthenticationFilter} to handle JWT authentication
     * @param userDetailsService the {@link UserDetailsService} to load user-specific data
     */
    public SecurityConfigInfrastructure(JwtAuthenticationFilter jwtAuthenticationFilter, UserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures the {@link SecurityFilterChain} for the application.
     * This method sets up the security rules such as disabling CSRF, defining
     * endpoint access rules and adding the JWT authentication filter.
     *
     * @param http the {@link HttpSecurity} object to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/api/v1/account").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/account/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(authenticationEntryPoint())
                )
                .build();
    }

    /**
     * Provides a custom {@link AuthenticationEntryPoint} to handle unauthorized access.
     * This entry point sends a 401 Unauthorized response with a custom error message.
     *
     * @return the configured {@link AuthenticationEntryPoint}
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Wrong credentials");
        };
    }

    /**
     * Configures the {@link AuthenticationProvider} for the application.
     * This provider uses {@link BCryptPasswordEncoder} for password encoding
     * and {@link UserDetailsService} for retrieving user details.
     *
     * @return the configured {@link AuthenticationProvider}
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /**
     * Provides the {@link AuthenticationManager} for the application.
     * This manager is used to authenticate users based on the provided configuration.
     *
     * @param config the {@link AuthenticationConfiguration} to use
     * @return the configured {@link AuthenticationManager}
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

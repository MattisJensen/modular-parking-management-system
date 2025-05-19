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
     * Constructs the security configuration with required dependencies.
     *
     * @param jwtAuthenticationFilter the {@link JwtAuthenticationFilter} that processes and validates JWT tokens in requests
     * @param userDetailsService the {@link UserDetailsService} that loads user-specific data for authentication and authorization
     */
    public SecurityConfigInfrastructure(JwtAuthenticationFilter jwtAuthenticationFilter, UserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures the security filter chain for HTTP requests.
     * <p>
     * This method defines the security rules for the application by:
     * <ul>
     *   <li>Disabling CSRF protection for stateless REST API</li>
     *   <li>Permitting unauthenticated access to account creation and login endpoints</li>
     *   <li>Requiring authentication for all other endpoints</li>
     *   <li>Configuring stateless session management (no HTTP sessions)</li>
     *   <li>Adding JWT filter before standard authentication filters</li>
     *   <li>Setting up custom handling for authentication failures</li>
     * </ul>
     *
     * @param http the {@link HttpSecurity} object to configure security settings
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during security configuration
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
     * Provides a custom authentication entry point for handling unauthorized access attempts.
     * <p>
     * This entry point is triggered when an unauthenticated user attempts to access a protected resource.
     * It responds with a 401 Unauthorized status code and a plain text error message.
     *
     * @return the configured {@link AuthenticationEntryPoint}
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Access denied.");
        };
    }

    /**
     * Configures the authentication provider for the application.
     * <p>
     * This authentication provider is responsible for verifying credentials during authentication.
     * It uses:
     * <ul>
     *   <li>{@link BCryptPasswordEncoder} for secure password comparison</li>
     *   <li>The injected {@link UserDetailsService} to retrieve user information</li>
     * </ul>
     *
     * @return the configured {@link AuthenticationProvider} for the application
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /**
     * Provides the authentication manager for the application.
     * <p>
     * The authentication manager is the main entry point for authentication requests.
     * It delegates to the appropriate {@link AuthenticationProvider} for credential validation.
     *
     * @param config the {@link AuthenticationConfiguration} from which to retrieve the authentication manager
     * @return the configured {@link AuthenticationManager}
     * @throws Exception if an error occurs during authentication manager configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

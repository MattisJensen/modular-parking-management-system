package dk.sdu.mmmi.pms.infrastructure.shared.authentication;

import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT authentication filter responsible for processing JWT tokens in request headers.
 * This filter extends {@link OncePerRequestFilter} to ensure it executes only once per request.
 * It verifies JWT tokens in the Authorization header and sets up the shared context
 * if the token is valid.
 * <p>
 * The filter works in the following sequence:
 * 1. Extract the JWT token from the Authorization header
 * 2. Extract the user attribute from the token using {@link JwtService}
 * 3. Load user details using {@link UserDetailsService}
 * 4. Validate the token against the user details
 * 5. Set up authentication in the {@link SecurityContextHolder} if token is valid
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Constructs a new JWT authentication filter.
     *
     * @param jwtService the {@link JwtService} used for token operations such as extraction and validation
     * @param userDetailsService the {@link UserDetailsService} used to load user information needed for authentication
     */
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Core filter method that processes each HTTP request to extract and validate JWT tokens.
     * This method handles the authentication process based on JWT tokens present in request headers.
     * If a valid token is found, it sets up the {@link SecurityContextHolder} with appropriate
     * authentication details.
     * <p>
     * For requests without valid tokens or authentication headers, the filter chain continues
     * without setting authentication.
     * <p>
     * In case of invalid tokens or authentication failures, a 401 Unauthorized response is returned.
     *
     * @param request the {@link HttpServletRequest} being processed
     * @param response the {@link HttpServletResponse} associated with the request
     * @param filterChain the {@link FilterChain} for executing subsequent filters
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs during processing
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String headerPrefix = "Bearer ";
            final String authorizationHeader = request.getHeader("Authorization");

            // Check if the Authorization header
            if (authorizationHeader == null || !authorizationHeader.startsWith(headerPrefix)) {
                filterChain.doFilter(request, response);
                return;
            }

            final String token = authorizationHeader.replace(headerPrefix, "");
            final String attribute = jwtService.extractAttribute(token);
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // If null = not authenticated yet

            // Validate the token and set authentication if valid
            if (attribute != null && authentication == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(attribute);

                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (BadCredentialsException | SignatureException ex) {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Access denied.");
            response.flushBuffer();
        }
    }
}

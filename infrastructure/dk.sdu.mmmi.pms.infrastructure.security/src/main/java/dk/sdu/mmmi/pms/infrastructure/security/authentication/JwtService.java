package dk.sdu.mmmi.pms.infrastructure.security.authentication;

import dk.sdu.mmmi.pms.application.shared.TokenManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service responsible for JWT (JSON Web Token) operations including generation, validation and claim extraction.
 * Implements {@link TokenManager} to provide a standardized token management interface.
 * This service uses HMAC-SHA256 algorithm for token signing and provides a 5-hour token expiration period.
 */
@Component
public class JwtService implements TokenManager {
    private final ApplicationContext context;
    private final String secretKey;

    /**
     * Constructs a new {@link JwtService} with the required dependencies.
     * Initializes the secret key for token signing.
     *
     * @param context the {@link ApplicationContext} for Spring dependency management
     */
    public JwtService(ApplicationContext context) {
        this.context = context;
        this.secretKey = getSecretKey();
    }


    /**
     * Generates a cryptographically secure secret key for JWT signing.
     * Uses the HmacSHA256 algorithm to generate the key and encodes it as a Base64 string.
     *
     * @return the generated secret key as a Base64-encoded string
     * @throws RuntimeException if the HmacSHA256 algorithm is not available
     */
    private String getSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a JWT token with the provided attribute as the subject.
     * The token includes issuance time and expiration claims and is signed using the secret key.
     * Token expiration time is set to 5 hours from creation.
     *
     * @param attribute the value to be stored as the subject claim in the token
     * @return the generated JWT token string
     */
    @Override
    public String generateToken(String attribute) {
        final long JWT_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 5; // 5 hours
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(attribute)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_TIME))
                .and()
                .signWith(getKey())
                .compact();

    }

    /**
     * Converts the Base64-encoded secret key into a {@link SecretKey} object
     * that can be used for JWT signing and verification.
     *
     * @return the {@link SecretKey} object for JWT operations
     */
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extracts the subject attribute from a JWT token.
     *
     * @param token the JWT token to extract the attribute from
     * @return the subject claim value from the token
     * @throws SignatureException if the token is invalid or tampered
     */
    @Override
    public String extractAttribute(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generic method to extract a specific claim from a JWT token.
     * Uses a function to specify which claim to extract.
     *
     * @param token the JWT token to extract the claim from
     * @param claimResolver a function that specifies which claim to extract
     * @param <T> the type of the claim value to return
     * @return the extracted claim value
     * @throws SignatureException if the token is invalid or tampered
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Extracts all claims from a JWT token.
     * Parses the token using the secret key and verifies its signature.
     *
     * @param token the JWT token to extract claims from
     * @return the {@link Claims} object containing all claims from the token
     * @throws SignatureException if the token signature is invalid or if any other JWT exception occurs
     */
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException ex) {
            throw new SignatureException("Access denied.");
        }

    }

    /**
     * Validates a JWT token against a {@link UserDetails} object.
     * A token is considered valid if its subject matches the username in the {@link UserDetails}
     * and the token is not expired.
     *
     * @param token the JWT token to validate
     * @param userDetails the {@link UserDetails} to validate against
     * @return true if the token is valid for the given user details, false otherwise
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String attribute = extractAttribute(token);
        return (attribute.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if a JWT token has expired.
     * A token is considered expired if its expiration date is before the current time.
     *
     * @param token the JWT token to check
     * @return true if the token has expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token the JWT token to extract the expiration date from
     * @return the {@link Date} representing the token's expiration time
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
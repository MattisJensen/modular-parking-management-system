package dk.sdu.mmmi.pms.infrastructure.account.authentication;

import dk.sdu.mmmi.pms.application.account.uitl.AccountValidator;
import dk.sdu.mmmi.pms.application.shared.TokenManager;
import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link AccountValidator} that integrates with Spring Security's authentication system.
 * <p>
 * This class validates account credentials by delegating to Spring Security's {@link AuthenticationManager}
 * and generates authentication tokens for successfully authenticated accounts using the {@link TokenManager}.
 * <p>
 * The validator works by:
 * <ul>
 *   <li>Converting the {@link Account} credentials to a Spring Security authentication token</li>
 *   <li>Delegating authentication to the configured {@link AuthenticationManager}</li>
 *   <li>Generating a JWT token for successfully authenticated accounts</li>
 * </ul>
 * <p>
 * This implementation bridges the application's domain authentication requirements with
 * Spring Security's authentication infrastructure.
 */
@Component
public class AccountValidatorImpl implements AccountValidator {
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    /**
     * Constructs a new {@link AccountValidatorImpl} with the required dependencies.
     *
     * @param authenticationManager the Spring {@link AuthenticationManager} used to authenticate credentials
     * @param tokenManager the {@link TokenManager} used to generate authentication tokens for validated accounts
     */
    public AccountValidatorImpl(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    /**
     * Verifies account credentials and generates an authentication token if successful.
     * <p>
     * This method authenticates the account using Spring Security's authentication manager.
     * It converts the account credentials to a {@link UsernamePasswordAuthenticationToken} and
     * delegates the authentication process to the {@link AuthenticationManager}.
     * <p>
     * For successfully authenticated accounts, a JWT token is generated using the account's
     * email as the token subject.
     *
     * @param account the {@link Account} containing the credentials to verify
     * @return a JWT token string if authentication succeeds or "fail" if authentication fails
     */
    @Override
    public String authenticateAccount(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if (authentication.isAuthenticated()) {
            return tokenManager.generateToken(email);
        } else {
            return "fail";
        }
    }
}

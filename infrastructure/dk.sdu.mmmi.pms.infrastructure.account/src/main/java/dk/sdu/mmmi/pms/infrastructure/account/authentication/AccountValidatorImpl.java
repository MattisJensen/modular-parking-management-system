package dk.sdu.mmmi.pms.infrastructure.account.authentication;

import dk.sdu.mmmi.pms.application.account.AccountValidator;
import dk.sdu.mmmi.pms.application.shared.TokenManager;
import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AccountValidatorImpl implements AccountValidator {
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    public AccountValidatorImpl(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    @Override
    public String verify(Account account) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.email(), account.password()));
        if (authentication.isAuthenticated()) {
            return tokenManager.generateToken(account.email());
        } else {
            return "fail";
        }
    }
}

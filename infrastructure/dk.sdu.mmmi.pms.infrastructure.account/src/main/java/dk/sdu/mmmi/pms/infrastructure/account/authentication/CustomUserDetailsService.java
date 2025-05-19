package dk.sdu.mmmi.pms.infrastructure.account.authentication;

import dk.sdu.mmmi.pms.application.account.usecase.FindAccountByEmailUseCase;
import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of Spring Security's {@link UserDetailsService} that integrates
 * the application's account system with Spring Security's authentication mechanisms.
 * <p>
 * This service bridges the application's domain model ({@link Account}) with Spring Security's
 * authentication system by converting {@link Account} objects to {@link UserDetails} objects
 * that Spring Security can use for authentication and authorization processes.
 * <p>
 * The service delegates the account retrieval to the {@link FindAccountByEmailUseCase},
 * which follows the clean architecture pattern to access account data. Once retrieved,
 * the account is wrapped in a {@link CustomUserDetail} object to match Spring Security's
 * expected interfaces.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final FindAccountByEmailUseCase findAccountByEmailUseCase;

    /**
     * Constructs a new {@link CustomUserDetailsService} with the necessary dependencies.
     *
     * @param findAccountByEmailUseCase the use case responsible for retrieving {@link Account} objects by email address
     */
    public CustomUserDetailsService(FindAccountByEmailUseCase findAccountByEmailUseCase) {
        this.findAccountByEmailUseCase = findAccountByEmailUseCase;
    }

    /**
     * Loads a user's details by username (email in this implementation).
     * <p>
     * This method is called by Spring Security during authentication processes to retrieve
     * user details. In this implementation, the username parameter of the interface represents the account's
     * email address.
     * <p>
     * The method delegates to {@link FindAccountByEmailUseCase} to retrieve the {@link Account}
     * entity and then wraps it in a {@link CustomUserDetail} object which implements
     * Spring Security's {@link UserDetails} interface.
     *
     * @param email the email address of the account to load
     * @return a {@link UserDetails} object containing the account's authentication and authorization details
     * @throws UsernameNotFoundException if no account is found with the provided email address
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Account account = findAccountByEmailUseCase.execute(email);
        return new CustomUserDetail(account);
    }
}

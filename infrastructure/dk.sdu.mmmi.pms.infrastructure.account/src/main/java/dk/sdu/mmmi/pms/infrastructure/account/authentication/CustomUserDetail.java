package dk.sdu.mmmi.pms.infrastructure.account.authentication;

import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Adapter class that bridges the application's {@link Account} domain model with Spring Security's
 * authentication system.
 * <p>
 * This class implements the Spring Security {@link UserDetails} interface to provide the necessary
 * authentication and authorization information for an {@link Account}. It serves as an adapter
 * between the domain model and Spring Security's authentication framework.
 * <p>
 * The implementation:
 * <ul>
 *   <li>Maps the account's role to Spring Security authorities</li>
 *   <li>Provides account credentials for authentication</li>
 *   <li>Sets default values for account status properties (always active in this implementation)</li>
 * </ul>
 * <p>
 * This class is used by {@link CustomUserDetailsService} to convert domain {@link Account} objects
 * into objects that Spring Security can use for authentication and authorization.
 */
public class CustomUserDetail implements UserDetails {
    private final Account account;

    /**
     * Constructs a new {@link CustomUserDetail} that wraps the specified account.
     *
     * @param account the domain {@link Account} object to adapt for Spring Security
     */
    public CustomUserDetail(Account account) {
        this.account = account;
    }

    /**
     * Retrieves the authorities granted to the account.
     * <p>
     * This implementation converts the account's role into a Spring Security
     * {@link GrantedAuthority} which determines what actions the account can perform
     * in the application.
     *
     * @return a collection containing the account's granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = account.accountRole().name();
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    /**
     * Retrieves the password used for authentication.
     * <p>
     * This implementation returns the account's password which is used by
     * Spring Security during the authentication process.
     *
     * @return the account's password
     */
    @Override
    public String getPassword() {
        return account.password();
    }

    /**
     * Retrieves the username used for authentication.
     * <p>
     * This implementation uses the account's email address as the username
     * for authentication purposes.
     *
     * @return the account's email address
     */
    @Override
    public String getUsername() {
        return account.email();
    }

    /**
     * Indicates whether the account has expired.
     * <p>
     * This implementation always returns true, meaning the account
     * never expires.
     *
     * @return true indicating the account is always non-expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the account is locked.
     * <p>
     * This implementation always returns true, meaning the account
     * is never locked.
     *
     * @return true indicating the account is always non-locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the account's credentials have expired.
     * <p>
     * This implementation always returns true, meaning the credentials
     * never expire.
     *
     * @return true indicating the credentials are always non-expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the account is enabled.
     * <p>
     * This implementation always returns true, meaning the account
     * is always enabled.
     *
     * @return true indicating the account is always enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

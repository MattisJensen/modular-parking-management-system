package dk.sdu.mmmi.pms.infrastructure.account.authentication;

import dk.sdu.mmmi.pms.application.account.usecase.FindAccountByEmailUseCase;
import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final FindAccountByEmailUseCase findAccountByEmailUseCase;

    public CustomUserDetailsService(FindAccountByEmailUseCase findAccountByEmailUseCase) {
        this.findAccountByEmailUseCase = findAccountByEmailUseCase;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Account account = findAccountByEmailUseCase.execute(username);
        return new CustomUserDetail(account);
    }
}

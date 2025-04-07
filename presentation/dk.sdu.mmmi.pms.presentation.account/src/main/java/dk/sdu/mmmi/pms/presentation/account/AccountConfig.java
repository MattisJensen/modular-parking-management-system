package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.CreateAccountUseCase;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {
    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return new CreateAccountUseCase(accountRepository, passwordEncoder);
    }
}



package dk.sdu.mmmi.pms.presentation.account.config;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.usecase.CreateAccountUseCase;
import dk.sdu.mmmi.pms.application.account.usecase.FindAccountByEmailUseCase;
import dk.sdu.mmmi.pms.application.account.usecase.FindAccountByIdUseCase;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.presentation.account")
public class AccountConfigPresentation {
    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return new CreateAccountUseCase(accountRepository, passwordEncoder);
    }

    @Bean
    public FindAccountByEmailUseCase findAccountByEmailUseCase(AccountRepository accountRepository) {
        return new FindAccountByEmailUseCase(accountRepository);
    }

    @Bean
    public FindAccountByIdUseCase findAccountByIdUseCase(AccountRepository accountRepository) {
        return new FindAccountByIdUseCase(accountRepository);
    }
}



package dk.sdu.mmmi.pms.presentation.account.config;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.usecase.CreateAccountUseCase;
import dk.sdu.mmmi.pms.application.account.usecase.FindAccountByEmailUseCase;
import dk.sdu.mmmi.pms.application.account.usecase.FindAccountByIdUseCase;
import dk.sdu.mmmi.pms.application.account.usecase.UpdateAccountUseCase;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the account presentation module.
 * This class is annotated with {@link Configuration} to indicate that it provides
 * Spring configuration and {@link ComponentScan} to scan for Spring components
 * within its module.
 */
@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.presentation.account")
public class AccountConfigPresentation {

    /**
     * Provides a {@link CreateAccountUseCase} bean.
     *
     * @param accountRepository the {@link AccountRepository} to handle account storage
     * @param passwordEncoder   the {@link PasswordEncoder} to hash passwords
     * @return a {@link CreateAccountUseCase} instance
     */
    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return new CreateAccountUseCase(accountRepository, passwordEncoder);
    }

    /**
     * Provides an {@link UpdateAccountUseCase} bean.
     *
     * @param accountRepository the {@link AccountRepository} to handle account storage
     * @param passwordEncoder   the {@link PasswordEncoder} to hash passwords
     * @return an {@link UpdateAccountUseCase} instance
     */
    @Bean
    public UpdateAccountUseCase updateAccountUseCase(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return new UpdateAccountUseCase(accountRepository, passwordEncoder);
    }

    /**
     * Provides a {@link FindAccountByEmailUseCase} bean.
     *
     * @param accountRepository the {@link AccountRepository} to handle account retrieval
     * @return a {@link FindAccountByEmailUseCase} instance
     */
    @Bean
    public FindAccountByEmailUseCase findAccountByEmailUseCase(AccountRepository accountRepository) {
        return new FindAccountByEmailUseCase(accountRepository);
    }

    /**
     * Provides a {@link FindAccountByIdUseCase} bean.
     *
     * @param accountRepository the {@link AccountRepository} to handle account retrieval
     * @return a {@link FindAccountByIdUseCase} instance
     */
    @Bean
    public FindAccountByIdUseCase findAccountByIdUseCase(AccountRepository accountRepository) {
        return new FindAccountByIdUseCase(accountRepository);
    }
}



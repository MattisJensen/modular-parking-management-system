package dk.sdu.mmmi.pms.infrastructure.account.config;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.AccountValidator;
import dk.sdu.mmmi.pms.application.account.usecase.*;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for the account infrastructure module.
 * This class is annotated with {@link Configuration} to indicate that it provides
 * Spring configuration, {@link EnableJpaRepositories} to enable JPA repositories,
 * and {@link ComponentScan} to scan for Spring components within its module.
 */
@Configuration
@EnableJpaRepositories(basePackages = "dk.sdu.mmmi.pms.infrastructure.account")
@ComponentScan(basePackages = "dk.sdu.mmmi.pms.infrastructure.account")
public class AccountConfigInfrastructure {
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
     * Provides a {@link DeleteAccountByIdUseCase} bean.
     *
     * @param accountRepository the {@link AccountRepository} to handle account deletion
     * @return a {@link DeleteAccountByIdUseCase} instance
     */
    @Bean
    public DeleteAccountByIdUseCase deleteAccountByIdUseCase(AccountRepository accountRepository) {
        return new DeleteAccountByIdUseCase(accountRepository);
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

    /**
     * Provides a {@link LoginByEmailUseCase} bean.
     *
     * @param findAccountByEmailUseCase the {@link FindAccountByEmailUseCase} to find accounts by email
     * @param accountValidator          the {@link AccountValidator} to validate accounts
     * @return a {@link LoginByEmailUseCase} instance
     */
    @Bean
    public LoginByEmailUseCase loginByEmailUseCase(FindAccountByEmailUseCase findAccountByEmailUseCase, AccountValidator accountValidator) {
        return new LoginByEmailUseCase(findAccountByEmailUseCase, accountValidator);
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
}
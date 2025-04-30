package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.EmailValidator;
import dk.sdu.mmmi.pms.application.account.IdValidator;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import dk.sdu.mmmi.pms.core.account.exceptions.DuplicateEmailException;

import java.util.UUID;

public class UpdateAccountUseCase {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdateAccountUseCase(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(UUID id, UpdateParameters parameters) {
        Account existingAccount = IdValidator.validateExistenceAndGetAccount(id, accountRepository);
        Account updatedAccount = createUpdatedAccount(existingAccount, parameters);
        validateEmail(parameters.email);
        EmailValidator.validateUniqueness(parameters.email, accountRepository);
        accountRepository.update(updatedAccount);
    }

    private Account createUpdatedAccount(Account existing, UpdateParameters params) {
        return new Account(
                existing.id(),
                params.name() != null ? params.name() : existing.name(),
                params.email() != null ? params.email : existing.email(),
                params.password() != null ? passwordEncoder.encode(params.password()) : existing.password(),
                params.role() != null ? params.role() : existing.accountRole()
        );
    }

    private void validateEmail(String email) throws IllegalArgumentException, DuplicateEmailException {
        EmailValidator.validateFormat(email);
        EmailValidator.validateUniqueness(email, accountRepository);
    }

    public record UpdateParameters(
            String name,
            String email,
            String password,
            AccountRole role
    ) {}
}
package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.uitl.EmailValidator;
import dk.sdu.mmmi.pms.application.account.uitl.IdValidator;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailDuplicateException;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailFormatException;

import java.util.UUID;

/**
 * Use case for updating an existing {@link Account}.
 * This class encapsulates the logic for validating input data, updating account details
 * and saving the updated account in the {@link AccountRepository}.
 */
public class UpdateAccountUseCase {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for {@link UpdateAccountUseCase}.
     *
     * @param accountRepository the repository to handle account updates
     * @param passwordEncoder   the encoder to hash passwords
     */
    public UpdateAccountUseCase(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Executes the use case to update an existing {@link Account}.
     * Validates the account ID, updates the account details and saves the updated account.
     *
     * @param id         the unique identifier of the account to update
     * @param parameters the parameters containing the updated account details
     * @throws EmailFormatException    if the email format is invalid
     * @throws EmailDuplicateException if the email is already in use
     */
    public void execute(UUID id, UpdateParameters parameters) {
        Account existingAccount = IdValidator.validateExistenceAndGetAccount(id, accountRepository);
        Account updatedAccount = createUpdatedAccount(existingAccount, parameters);
        if (parameters.email() != null) {
            validateEmail(parameters.email());
        }
        EmailValidator.validateUniqueness(parameters.email, accountRepository);
        accountRepository.update(updatedAccount);
    }

    /**
     * Creates an updated {@link Account} based on the existing account and the provided parameters.
     *
     * @param existing the existing account to update
     * @param params   the parameters containing the updated account details
     * @return the updated {@link Account}
     */
    private Account createUpdatedAccount(Account existing, UpdateParameters params) {
        return new Account(
                existing.id(),
                params.name() != null ? params.name() : existing.name(),
                params.email() != null ? params.email : existing.email(),
                params.password() != null ? passwordEncoder.encode(params.password()) : existing.password(),
                params.role() != null ? params.role() : existing.accountRole()
        );
    }

    /**
     * Validates the email format and uniqueness.
     *
     * @param email the email address to validate
     */
    private void validateEmail(String email) {
        EmailValidator.validateFormat(email);
        EmailValidator.validateUniqueness(email, accountRepository);
    }

    /**
     * Record for encapsulating the parameters required to update an {@link Account}.
     */
    public record UpdateParameters(
            String name,
            String email,
            String password,
            AccountRole role
    ) {
    }
}
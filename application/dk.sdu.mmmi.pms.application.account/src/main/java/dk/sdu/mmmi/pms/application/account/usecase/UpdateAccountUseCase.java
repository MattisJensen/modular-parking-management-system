package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.uitl.EmailValidator;
import dk.sdu.mmmi.pms.application.account.uitl.IdValidator;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import dk.sdu.mmmi.pms.core.account.exception.EmailDuplicateException;
import dk.sdu.mmmi.pms.core.account.exception.EmailFormatException;

import java.util.UUID;

/**
 * Use case for updating an existing {@link Account}.
 * This class encapsulates the logic for validating input data, updating account details
 * and saving the updated account in the {@link AccountRepository}.
 */
public class UpdateAccountUseCase {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for {@link UpdateAccountUseCase}.
     *
     * @param repository the repository to handle account updates
     * @param passwordEncoder   the encoder to hash passwords
     */
    public UpdateAccountUseCase(AccountRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
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
        Account existingAccount = IdValidator.validateExistenceAndGetAccount(id, repository);
        Account updatedAccount = createUpdatedAccount(existingAccount, parameters);
        if (parameters.email() != null) validateEmail(parameters.email());

        EmailValidator.validateUniqueness(parameters.email, repository);
        repository.update(updatedAccount);
    }

    /**
     * Creates an updated {@link Account} based on the existing account and the provided parameters.
     *
     * @param existing the existing account to update
     * @param parameters   the parameters containing the updated account details
     * @return the updated {@link Account}
     */
    private Account createUpdatedAccount(Account existing, UpdateParameters parameters) {
        return new Account(
                existing.id(),
                parameters.name() != null ? parameters.name() : existing.name(),
                parameters.email() != null ? parameters.email : existing.email(),
                parameters.password() != null ? passwordEncoder.encode(parameters.password()) : existing.password(),
                parameters.role() != null ? parameters.role() : existing.accountRole()
        );
    }

    /**
     * Validates the email format and uniqueness.
     *
     * @param email the email address to validate
     */
    private void validateEmail(String email) {
        EmailValidator.validateFormat(email);
        EmailValidator.validateUniqueness(email, repository);
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
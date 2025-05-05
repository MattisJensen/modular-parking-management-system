package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.uitl.EmailValidator;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import dk.sdu.mmmi.pms.core.account.exception.EmailDuplicateException;
import dk.sdu.mmmi.pms.core.account.exception.EmailFormatException;

import java.util.UUID;

/**
 * Use case for creating a new {@link Account}.
 * This class encapsulates the logic for validating input data, hashing passwords,
 * and storing the account in the {@link AccountRepository}.
 */
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for {@link CreateAccountUseCase}.
     *
     * @param accountRepository the repository to handle account storage
     * @param passwordEncoder   the encoder to hash passwords
     */
    public CreateAccountUseCase(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new {@link Account} with the given details.
     * Validates the email format and uniqueness, hashes the password and stores the account.
     *
     * @param name        the name of the account holder
     * @param email       the email address of the account holder
     * @param rawPassword the raw password to be hashed and stored
     * @param role        the role of the account (e.g., USER, ADMIN)
     * @return the UUID of the created account
     * @throws EmailFormatException    if the email format is invalid
     * @throws EmailDuplicateException if the email is already in use
     */
    public UUID execute(String name, String email, String rawPassword, AccountRole role) {
        EmailValidator.validateFormat(email);
        EmailValidator.validateUniqueness(email, accountRepository);
        UUID accountId = UUID.randomUUID();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        Account account = new Account(accountId, name, email, hashedPassword, role);
        accountRepository.save(account);
        return accountId;
    }
}

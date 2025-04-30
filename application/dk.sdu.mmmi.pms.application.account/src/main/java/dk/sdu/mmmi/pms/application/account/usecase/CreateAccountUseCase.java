package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.EmailValidator;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import java.util.UUID;

public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for CreateAccountUseCase.
     * @param accountRepository the repository to handle account storage
     * @param passwordEncoder the encoder to hash passwords
     */
    public CreateAccountUseCase(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new account with the given details.
     * @param name the name of the account holder
     * @param email the email address of the account holder
     * @param rawPassword the raw password to be hashed and stored
     * @param role the role of the account (e.g., USER, ADMIN)
     * @return the UUID of the created account
     * @throws IllegalArgumentException if the email format is invalid
     */
    public UUID execute(String name, String email, String rawPassword, AccountRole role) throws IllegalArgumentException {
        if (!EmailValidator.validate(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        String hashedPassword = passwordEncoder.encode(rawPassword);
        UUID accountId = UUID.randomUUID();
        Account account = new Account(accountId, name, email, hashedPassword, role);
        accountRepository.save(account);
        return accountId;
    }
}

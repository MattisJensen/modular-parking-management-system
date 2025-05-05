package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exception.AccountNotFoundException;

/**
 * Use case for finding an account by its email address.
 * This class encapsulates the logic for retrieving an {@link Account}
 * from the {@link AccountRepository} based on the provided ID.
 */
public class FindAccountByEmailUseCase {
    private final AccountRepository accountRepository;

    /**
     * Constructor for {@link FindAccountByEmailUseCase}.
     *
     * @param accountRepository the repository to handle account retrieval
     */
    public FindAccountByEmailUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Executes the use case to find an account by email.
     *
     * @param email the email address of the account to find
     * @return the account associated with the given email
     * @throws AccountNotFoundException if no account with the given email is found
     */
    public Account execute(String email) throws AccountNotFoundException {
        return accountRepository
                .findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException("Account with email " + email + " not found"));
    }
}
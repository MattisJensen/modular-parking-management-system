package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exception.AccountNotFoundException;

import java.util.UUID;

/**
 * Use case for finding an account by its ID.
 * This class encapsulates the logic for retrieving an {@link Account}
 * from the {@link AccountRepository} based on the provided ID.
 */
public class FindAccountByIdUseCase {
    private final AccountRepository accountRepository;

    /**
     * Constructor for {@link FindAccountByIdUseCase}.
     *
     * @param accountRepository the repository to handle account retrieval
     */
    public FindAccountByIdUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Executes the use case to find an account by its ID.
     *
     * @param id the unique identifier of the account to find
     * @return the {@link Account} associated with the given ID
     * @throws AccountNotFoundException if no account with the given ID is found
     */
    public Account execute(UUID id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id.toString() + " not found"));
    }
}

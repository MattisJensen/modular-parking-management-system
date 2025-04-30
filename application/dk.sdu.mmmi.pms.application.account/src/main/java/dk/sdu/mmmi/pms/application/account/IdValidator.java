package dk.sdu.mmmi.pms.application.account;

import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exceptions.AccountNotFoundException;

import java.util.UUID;

public class IdValidator {
    /**
     * Validates that an account with the given ID exists.
     *
     * @param id                the account ID to validate
     * @param accountRepository the repository to check for the account
     * @throws AccountNotFoundException if the account with the given ID does not exist
     */
    public static void validateExistence(UUID id, AccountRepository accountRepository) throws AccountNotFoundException {
        accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
    }

    /**
     * Validates that an account with the given ID exists and returns it.
     * Reduces Database calls by returning the already fetched account.
     *
     * @param id                the account ID to validate
     * @param accountRepository the repository to check for the account
     * @return the account if it exists
     * @throws AccountNotFoundException if the account with the given ID does not exist
     */
    public static Account validateExistenceAndGetAccount(UUID id, AccountRepository accountRepository) throws AccountNotFoundException {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
    }
}

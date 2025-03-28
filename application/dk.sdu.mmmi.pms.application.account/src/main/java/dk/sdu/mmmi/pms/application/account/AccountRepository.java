package dk.sdu.mmmi.pms.application.account;

import dk.sdu.mmmi.pms.core.account.Account;

import java.util.Optional;
import java.util.UUID;

/**
 * This interface defines the methods for managing accounts in the application.
 * It includes methods such as saving, updating and finding accounts.
 */
public interface AccountRepository {
    /**
     * Saves the given account to the repository.
     *
     * @param account the account to save
     */
    void save(Account account);

    /**
     * Updates the given account in the repository.
     *
     * @param account the account to update
     */
    void update(Account account);

    /**
     * Finds an account by its ID.
     *
     * @param id the ID of the account to find
     * @return an Optional containing the found account, or empty if not found
     */
    Optional<Account> findById(UUID id);

    /**
     * Deletes an account by its ID.
     *
     * @param id the ID of the account to delete
     */
    void deleteById(UUID id);

    /**
     * Finds an account by its email address.
     *
     * @param email the email address of the account to find
     * @return an Optional containing the found account, or empty if not found
     */
    Optional<Account> findByEmail(String email);
}
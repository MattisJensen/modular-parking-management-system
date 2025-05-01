package dk.sdu.mmmi.pms.application.account;

 import dk.sdu.mmmi.pms.core.account.Account;

 import java.util.Optional;
 import java.util.UUID;

 /**
  * This interface defines the methods for managing {@link Account} instances in the application.
  * It includes methods for saving, updating and finding accounts.
  */
 public interface AccountRepository {
     /**
      * Saves the given {@link Account} to the repository.
      *
      * @param account the {@link Account} to save
      */
     void save(Account account);

     /**
      * Updates the given {@link Account} in the repository.
      *
      * @param account the {@link Account} to update
      */
     void update(Account account);

     /**
      * Finds an {@link Account} by its ID.
      *
      * @param id the ID of the {@link Account} to find
      * @return an {@link Optional} containing the found {@link Account} or empty if not found
      */
     Optional<Account> findById(UUID id);

     /**
      * Deletes an {@link Account} by its ID.
      *
      * @param id the ID of the {@link Account} to delete
      */
     void deleteById(UUID id);

     /**
      * Finds an {@link Account} by its email address.
      *
      * @param email the email address of the {@link Account} to find
      * @return an {@link Optional} containing the found {@link Account} or empty if not found
      */
     Optional<Account> findByEmail(String email);
 }
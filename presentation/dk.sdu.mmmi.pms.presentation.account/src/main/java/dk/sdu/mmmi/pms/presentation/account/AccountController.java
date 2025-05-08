package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.application.account.usecase.*;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.presentation.account.datatransferobject.AccountResponse;
import dk.sdu.mmmi.pms.presentation.account.datatransferobject.CreateAccountRequest;
import dk.sdu.mmmi.pms.presentation.account.datatransferobject.UpdateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for managing accounts.
 * This controller provides endpoints for creating, updating and retrieving accounts
 * using the corresponding use cases.
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final CreateAccountUseCase createUseCase;
    private final UpdateAccountUseCase updateUseCase;
    private final FindAccountByIdUseCase findByIdUseCase;
    private final FindAccountByEmailUseCase findByEmailUseCase;
    private final DeleteAccountByIdUseCase deleteByIdUseCase;

    /**
     * Constructs an {@link AccountController} with the required use cases.
     *
     * @param createUseCase   the use case for creating accounts
     * @param updateUseCase   the use case for updating accounts
     * @param findByEmailUseCase the use case for finding accounts by email
     * @param findByIdUseCase the use case for finding accounts by ID
     */
    public AccountController(CreateAccountUseCase createUseCase,
                             UpdateAccountUseCase updateUseCase,
                             FindAccountByEmailUseCase findByEmailUseCase,
                             FindAccountByIdUseCase findByIdUseCase,
                             DeleteAccountByIdUseCase deleteByIdUseCase) {

        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findByEmailUseCase = findByEmailUseCase;
        this.deleteByIdUseCase = deleteByIdUseCase;
    }

   /**
    * Creates a new account.
    *
    * @param request the {@link CreateAccountRequest} containing account details
    * @return a {@link ResponseEntity} containing the created {@link AccountResponse}
    */
   @PostMapping
   public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
       Account account = createUseCase.execute(
               request.name(),
               request.email(),
               request.password(),
               request.role()
       );

       AccountResponse response = new AccountResponse(
               account.id(),
               account.name(),
               account.email(),
               account.accountRole()
       );

       return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }

    /**
     * Updates an existing account.
     *
     * @param id      the ID of the account to update
     * @param request the {@link UpdateAccountRequest} containing updated account details
     * @return a {@link ResponseEntity} indicating the result of the operation
     */
    @PatchMapping("/id/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequest request) {
        try {
            UUID uuid = UUID.fromString(id);
            updateUseCase.execute(uuid, new UpdateAccountUseCase.UpdateParameters(
                    request.name(),
                    request.email(),
                    request.password(),
                    request.role()
            ));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param id the ID of the account to retrieve
     * @return a {@link ResponseEntity} containing the {@link AccountResponse}
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id); // Validate UUID format
            Account account = findByIdUseCase.execute(uuid);
            AccountResponse response = new AccountResponse(
                    account.id(),
                    account.name(),
                    account.email(),
                    account.accountRole()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }

    /**
     * Retrieves an account by its email.
     *
     * @param email the email of the account to retrieve
     * @return the {@link AccountResponse} for the account
     */
    @GetMapping("/email/{email}")
    public AccountResponse getAccountByEmail(@PathVariable String email) {
        Account account = findByEmailUseCase.execute(email);
        return new AccountResponse(
                account.id(),
                account.name(),
                account.email(),
                account.accountRole()
        );
    }

    /**
     * Deletes an account by its ID.
     *
     * @param id the ID of the account to delete
     * @return a {@link ResponseEntity} indicating the result of the operation
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            deleteByIdUseCase.execute(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }
}
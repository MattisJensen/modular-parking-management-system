package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.application.account.usecase.*;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exception.AccountNotFoundException;
import dk.sdu.mmmi.pms.core.account.exception.EmailFormatException;
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
@RequestMapping("/api/account")
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final FindAccountByIdUseCase findAccountByIdUseCase;
    private final FindAccountByEmailUseCase findAccountByEmailUseCase;
    private final DeleteAccountByIdUseCase deleteAccountByIdUseCase;

    /**
     * Constructs an {@link AccountController} with the required use cases.
     *
     * @param createAccountUseCase   the use case for creating accounts
     * @param updateAccountUseCase   the use case for updating accounts
     * @param findAccountByEmailUseCase the use case for finding accounts by email
     * @param findAccountByIdUseCase the use case for finding accounts by ID
     */
    public AccountController(CreateAccountUseCase createAccountUseCase,
                             UpdateAccountUseCase updateAccountUseCase,
                             FindAccountByEmailUseCase findAccountByEmailUseCase,
                             FindAccountByIdUseCase findAccountByIdUseCase,
                             DeleteAccountByIdUseCase deleteAccountByIdUseCase) {

        this.createAccountUseCase = createAccountUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
        this.findAccountByIdUseCase = findAccountByIdUseCase;
        this.findAccountByEmailUseCase = findAccountByEmailUseCase;
        this.deleteAccountByIdUseCase = deleteAccountByIdUseCase;
    }

   /**
    * Creates a new account.
    *
    * @param request the {@link CreateAccountRequest} containing account details
    * @return a {@link ResponseEntity} containing the created {@link AccountResponse}
    */
   @PostMapping("/create")
   public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
       UUID accountId = createAccountUseCase.execute(
               request.name(),
               request.email(),
               request.password(),
               request.role()
       );
       AccountResponse response = new AccountResponse(accountId, request.name(), request.email(), request.role());
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
            updateAccountUseCase.execute(uuid, new UpdateAccountUseCase.UpdateParameters(
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
            Account account = findAccountByIdUseCase.execute(uuid);
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
        Account account = findAccountByEmailUseCase.execute(email);
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
            deleteAccountByIdUseCase.execute(uuid);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + id);
        }
    }
}
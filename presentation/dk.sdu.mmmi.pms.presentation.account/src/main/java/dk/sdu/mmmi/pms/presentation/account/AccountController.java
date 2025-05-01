package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.application.account.usecase.CreateAccountUseCase;
import dk.sdu.mmmi.pms.application.account.usecase.FindAccountByEmailUseCase;
import dk.sdu.mmmi.pms.application.account.usecase.FindAccountByIdUseCase;
import dk.sdu.mmmi.pms.application.account.usecase.UpdateAccountUseCase;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exceptions.AccountNotFoundException;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailFormatException;
import dk.sdu.mmmi.pms.presentation.account.datatransferobjects.AccountResponse;
import dk.sdu.mmmi.pms.presentation.account.datatransferobjects.CreateAccountRequest;
import dk.sdu.mmmi.pms.presentation.account.datatransferobjects.UpdateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final FindAccountByIdUseCase findAccountByIdUseCase;
    private final FindAccountByEmailUseCase findAccountByEmailUseCase;


    public AccountController(CreateAccountUseCase createAccountUseCase,
                             UpdateAccountUseCase updateAccountUseCase,
                             FindAccountByEmailUseCase findAccountByEmailUseCase,
                             FindAccountByIdUseCase findAccountByIdUseCase) {

        this.createAccountUseCase = createAccountUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
        this.findAccountByIdUseCase = findAccountByIdUseCase;
        this.findAccountByEmailUseCase = findAccountByEmailUseCase;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        UUID accountId = createAccountUseCase.execute(
                request.name(),
                request.email(),
                request.password(),
                request.role()
        );
        return new AccountResponse(accountId, request.name(), request.email(), request.role());
    }

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
        } catch (EmailFormatException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

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
}
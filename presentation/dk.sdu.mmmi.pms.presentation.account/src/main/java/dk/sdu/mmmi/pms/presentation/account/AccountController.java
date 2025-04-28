package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.application.account.CreateAccountUseCase;
import dk.sdu.mmmi.pms.application.account.FindAccountByEmailUseCase;
import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final FindAccountByEmailUseCase findAccountByEmailUseCase;


    public AccountController(CreateAccountUseCase createAccountUseCase,
                             FindAccountByEmailUseCase findAccountByEmailUseCase) {
        this.createAccountUseCase = createAccountUseCase;
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

    @GetMapping("/email/{email}")
    public AccountResponse getAccountByEmail(@PathVariable String email) {
        Account account = findAccountByEmailUseCase.execute(email);
        return new AccountResponse(
                account.getId(),
                account.getName(),
                account.getEmail(),
                account.getAccountRole()
        );
    }
}
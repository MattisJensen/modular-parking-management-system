package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.application.account.CreateAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
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

    @GetMapping("/test4")
    public String test() {
        return "Account service is up and running!";
    }
}
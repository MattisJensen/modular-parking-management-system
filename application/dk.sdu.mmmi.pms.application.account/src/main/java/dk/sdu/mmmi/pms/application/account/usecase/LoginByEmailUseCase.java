package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.uitl.AccountValidator;
import dk.sdu.mmmi.pms.core.account.Account;

public class LoginByEmailUseCase {
    private final FindAccountByEmailUseCase findAccountByEmailUseCase;
    private final AccountValidator accountValidator;

    public LoginByEmailUseCase(FindAccountByEmailUseCase findAccountByEmailUseCase, AccountValidator accountValidator) {
        this.findAccountByEmailUseCase = findAccountByEmailUseCase;
        this.accountValidator = accountValidator;
    }

    public String execute(Account account) {
        findAccountByEmailUseCase.execute(account.email());
        return accountValidator.authenticateAccount(account.email(), account.password());
    }
}

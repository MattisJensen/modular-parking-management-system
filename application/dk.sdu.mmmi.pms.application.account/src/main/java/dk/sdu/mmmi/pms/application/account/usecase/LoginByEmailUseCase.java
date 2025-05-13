package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountValidator;
import dk.sdu.mmmi.pms.core.account.Account;

public class LoginByEmailUseCase {
    private final FindAccountByEmailUseCase findAccountByEmailUseCase;
    private final AccountValidator accountValidator;

    public LoginByEmailUseCase(FindAccountByEmailUseCase findAccountByEmailUseCase, AccountValidator accountValidator) {
        this.findAccountByEmailUseCase = findAccountByEmailUseCase;
        this.accountValidator = accountValidator;
    }

    public String execute(Account account) {
        Account foundAccount = findAccountByEmailUseCase.execute(account.email());

        account = new Account(
                account.id() == null ? foundAccount.id() : account.id(),
                account.name() == null ? foundAccount.name() : account.name(),
                account.email(),
                account.password(),
                account.accountRole() == null ? foundAccount.accountRole() : account.accountRole()
        );

        return accountValidator.verify(account);
    }
}

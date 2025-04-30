package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exceptions.AccountNotFoundException;

import java.util.UUID;

public class FindAccountByIdUseCase {
    private final AccountRepository accountRepository;

    public FindAccountByIdUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(UUID id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id.toString() + " not found"));
    }
}

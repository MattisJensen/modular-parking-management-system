package dk.sdu.mmmi.pms.application.account;

import dk.sdu.mmmi.pms.core.account.Account;

public class FindAccountByEmailUseCase {
    private final AccountRepository accountRepository;

    public FindAccountByEmailUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException("Account with email " + email + " not found"));
    }
}

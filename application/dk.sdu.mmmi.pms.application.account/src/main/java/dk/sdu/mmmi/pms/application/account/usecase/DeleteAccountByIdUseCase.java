package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.exception.AccountNotFoundException;

import java.util.UUID;

public class DeleteAccountByIdUseCase {
    private final AccountRepository repository;

    public DeleteAccountByIdUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        if (repository.findById(id).isEmpty()) throw new AccountNotFoundException("Account not found with id: " + id);
        repository.deleteById(id);
    }
}

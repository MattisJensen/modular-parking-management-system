package dk.sdu.mmmi.pms.infrastructure.account;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exceptions.DuplicateEmailException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * AccountRepositoryImpl is the implementation of the {@link AccountRepository} interface.
 * It uses {@link AccountJpaRepository} to perform CRUD operations on the database.
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository springDataRepo;
    private final AccountMapper mapper;

    public AccountRepositoryImpl(AccountJpaRepository springDataRepo, AccountMapper mapper) {
        this.springDataRepo = springDataRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(Account account) {
        try {
            AccountJpaEntity jpaEntity = mapper.toJpaEntity(account);
            springDataRepo.save(jpaEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("An account with the email " + account.getEmail() + " already exists.");
        }
    }

    @Override
    public void update(Account account) {
        AccountJpaEntity jpaEntity = mapper.toJpaEntity(account);
        springDataRepo.save(jpaEntity);
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepo.deleteById(id);
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return springDataRepo.findById(id)
                .map(mapper::toCore);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return springDataRepo.findByEmail(email)
                .map(mapper::toCore);
    }
}
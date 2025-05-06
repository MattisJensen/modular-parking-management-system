package dk.sdu.mmmi.pms.infrastructure.account;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.infrastructure.account.persistence.AccountJpaEntity;
import dk.sdu.mmmi.pms.infrastructure.account.persistence.AccountJpaRepository;
import dk.sdu.mmmi.pms.infrastructure.account.persistence.AccountMapper;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the {@link AccountRepository} interface.
 * This class uses {@link AccountJpaRepository} to perform CRUD operations
 * and {@link AccountMapper} to map between domain and persistence models.
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository springDataRepo;
    private final AccountMapper mapper;

    /**
     * Constructs a new {@link AccountRepositoryImpl} with the specified dependencies.
     *
     * @param springDataRepo the {@link AccountJpaRepository} to handle database operations
     * @param mapper         the {@link AccountMapper} to map between domain and persistence models
     */
    public AccountRepositoryImpl(AccountJpaRepository springDataRepo, AccountMapper mapper) {
        this.springDataRepo = springDataRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(Account account) {
        AccountJpaEntity jpaEntity = mapper.toJpaEntity(account);
        springDataRepo.save(jpaEntity);
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
        return springDataRepo.findById(id).map(mapper::toCore);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return springDataRepo.findByEmail(email)
                .map(mapper::toCore);
    }
}
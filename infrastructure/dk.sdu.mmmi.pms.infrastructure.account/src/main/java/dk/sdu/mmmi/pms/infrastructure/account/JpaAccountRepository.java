package dk.sdu.mmmi.pms.infrastructure.account;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.infrastructure.account.jpa.AccountJpaEntity;
import dk.sdu.mmmi.pms.infrastructure.account.jpa.AccountMapper;
import dk.sdu.mmmi.pms.infrastructure.account.jpa.AccountJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaAccountRepository implements AccountRepository {
    private final AccountJpaRepository springDataRepo;
    private final AccountMapper mapper;

    public JpaAccountRepository(AccountJpaRepository springDataRepo, AccountMapper mapper) {
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
    public Optional<Account> findById(UUID id) {
        return springDataRepo.findById(id)
                .map(mapper::toCore);
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepo.deleteById(id);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return Optional.empty();
    }
}
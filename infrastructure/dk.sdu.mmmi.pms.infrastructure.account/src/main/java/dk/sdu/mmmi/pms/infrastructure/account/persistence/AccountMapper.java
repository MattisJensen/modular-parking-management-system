package dk.sdu.mmmi.pms.infrastructure.account.persistence;

import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.stereotype.Component;

/**
 * AccountMapper is responsible for converting between {@link Account} and {@link AccountJpaEntity}.
 * It handles the conversion between the domain model and the persistence model.
 */
@Component
public class AccountMapper {
    public AccountJpaEntity toJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.id(),
                account.name(),
                account.email(),
                account.password(),
                account.accountRole()
        );
    }

    public Account toCore(AccountJpaEntity jpaEntity) {
        return new Account(
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getEmail(),
                jpaEntity.getPassword(),
                jpaEntity.getAccountRole()
        );
    }
}
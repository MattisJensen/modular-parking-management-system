package dk.sdu.mmmi.pms.infrastructure.account;

import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.stereotype.Component;

/**
 * AccountMapper is responsible for converting between Account and AccountJpaEntity.
 * It provides methods to convert from the core domain model to the JPA entity and vice versa.
 */
@Component
public class AccountMapper {
    public AccountJpaEntity toJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.getId(),
                account.getName(),
                account.getEmail(),
                account.getPassword(),
                account.getAccountRole()
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
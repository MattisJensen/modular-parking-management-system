package dk.sdu.mmmi.pms.infrastructure.account.jpa;

import dk.sdu.mmmi.pms.core.account.Account;

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
package dk.sdu.mmmi.pms.infrastructure.account.persistence;

import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.stereotype.Component;

/**
 * Maps between the domain model {@link Account} and the persistence model {@link AccountJpaEntity}.
 */
@Component
public class AccountMapper {

    /**
     * Converts a domain model {@link Account} to a persistence model {@link AccountJpaEntity}.
     *
     * @param account the {@link Account} to be converted
     * @return the corresponding {@link AccountJpaEntity}
     */
    public AccountJpaEntity toJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.id(),
                account.name(),
                account.email(),
                account.password(),
                account.accountRole()
        );
    }

    /**
     * Converts a persistence model {@link AccountJpaEntity} to a domain model {@link Account}.
     *
     * @param jpaEntity the {@link AccountJpaEntity} to be converted
     * @return the corresponding {@link Account}
     */
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
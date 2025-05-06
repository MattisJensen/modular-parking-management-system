package dk.sdu.mmmi.pms.infrastructure.account.persistence;

import dk.sdu.mmmi.pms.core.account.Account;
import org.springframework.stereotype.Component;

/**
 * Maps between the core model {@link Account} and the persistence model {@link AccountJpaEntity}.
 */
@Component
public class AccountMapper {

    /**
     * Converts a core model {@link Account} to a persistence model {@link AccountJpaEntity}.
     *
     * @param core the {@link Account} to be converted
     * @return the corresponding {@link AccountJpaEntity}
     */
    public AccountJpaEntity toJpaEntity(Account core) {
        return new AccountJpaEntity(
                core.id(),
                core.name(),
                core.email(),
                core.password(),
                core.accountRole()
        );
    }

    /**
     * Converts a persistence model {@link AccountJpaEntity} to a core model {@link Account}.
     *
     * @param jpa the {@link AccountJpaEntity} to be converted
     * @return the corresponding {@link Account}
     */
    public Account toCore(AccountJpaEntity jpa) {
        return new Account(
                jpa.getId(),
                jpa.getName(),
                jpa.getEmail(),
                jpa.getPassword(),
                jpa.getAccountRole()
        );
    }
}
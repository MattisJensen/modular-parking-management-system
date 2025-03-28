package dk.sdu.mmmi.pms.infrastructure.account.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringDataAccountRepository extends JpaRepository<AccountJpaEntity, UUID> {
}
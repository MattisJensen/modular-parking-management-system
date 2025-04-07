package dk.sdu.mmmi.pms.infrastructure.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Account entity.
 * This interface extends JpaRepository to provide CRUD operations on AccountJpaEntity.
 */
@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity, UUID> {
    Optional<AccountJpaEntity> findByEmail(String email);
}
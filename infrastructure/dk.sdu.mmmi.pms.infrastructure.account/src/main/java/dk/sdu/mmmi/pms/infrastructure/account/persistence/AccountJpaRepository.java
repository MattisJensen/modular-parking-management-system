package dk.sdu.mmmi.pms.infrastructure.account.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the {@link AccountJpaEntity}.
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * and custom query methods for the {@link AccountJpaEntity}.
 */
@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity, UUID> {

    /**
     * Finds an {@link AccountJpaEntity} by its email address.
     *
     * @param email the email address to search for
     * @return an {@link Optional} containing the {@link AccountJpaEntity} if found or empty if not found
     */
    Optional<AccountJpaEntity> findByEmail(String email);
}
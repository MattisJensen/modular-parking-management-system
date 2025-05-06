package dk.sdu.mmmi.pms.infrastructure.account.persistence;

import dk.sdu.mmmi.pms.core.account.AccountRole;
import jakarta.persistence.*;

import java.util.UUID;

/**
 * JPA entity representing an Account in the database.
 * This class is mapped to the "accounts" table and contains fields for account details
 * such as ID, name, email, password and role.
 */
@Entity
@Table(name = "accounts")
public class AccountJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountRole accountRole;

    /**
     * Default constructor for {@link AccountJpaEntity}.
     * This constructor is required by JPA.
     */
    public AccountJpaEntity() {}

    /**
     * Constructs a new {@link AccountJpaEntity} with the specified details.
     *
     * @param id          the unique identifier of the account
     * @param name        the name of the account holder
     * @param email       the email address of the account holder
     * @param password    the hashed password of the account
     * @param accountRole the role of the account (e.g., USER or ADMIN)
     */
    public AccountJpaEntity(UUID id, String name, String email, String password, AccountRole accountRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountRole = accountRole;
    }

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public AccountRole getAccountRole() { return accountRole; }
}
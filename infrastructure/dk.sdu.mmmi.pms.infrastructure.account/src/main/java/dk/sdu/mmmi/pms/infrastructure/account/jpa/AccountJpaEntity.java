package dk.sdu.mmmi.pms.infrastructure.account.jpa;

import dk.sdu.mmmi.pms.core.account.AccountRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountJpaEntity {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private AccountRole accountRole;

    public AccountJpaEntity() {}

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
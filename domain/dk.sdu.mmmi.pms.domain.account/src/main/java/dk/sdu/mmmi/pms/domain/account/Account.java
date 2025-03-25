package dk.sdu.mmmi.pms.domain.account;

import java.util.UUID;

public class Account {
    private final UUID id;
    private final String name;
    private final String email;
    private final AccountRole accountRole;

    public Account(UUID id, String name, String email, AccountRole accountRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountRole = accountRole;
    }

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public AccountRole getAccountRole() { return accountRole; }
}
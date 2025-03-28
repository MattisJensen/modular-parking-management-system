package dk.sdu.mmmi.pms.core.account;

import java.util.UUID;

public class Account {
    private final UUID id;
    private final String name;
    private final String email;
    private final String password;
    private final AccountRole accountRole;

    /**
     * Constructor for creating a new account.
     *
     * @param id          Unique identifier for the account.
     * @param name        Name of the account holder.
     * @param email       Email address of the account holder.
     * @param password    Password for the account.
     * @param accountRole Role of the account (e.g., USER, ADMIN).
     */
    public Account(UUID id, String name, String email, String password, AccountRole accountRole) {
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
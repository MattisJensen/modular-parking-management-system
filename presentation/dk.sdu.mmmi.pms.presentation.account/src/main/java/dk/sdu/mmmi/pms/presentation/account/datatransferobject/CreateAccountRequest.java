package dk.sdu.mmmi.pms.presentation.account.datatransferobject;

import dk.sdu.mmmi.pms.core.account.AccountRole;

/**
 * Data transfer object representing a request to create a new account.
 * This record encapsulates the necessary details for creating an account,
 * including the name, email, password and role.
 *
 * @param name     the name of the account holder
 * @param email    the email address of the account holder
 * @param password the raw password for the account
 * @param role     the {@link AccountRole} assigned to the account
 */
public record CreateAccountRequest(
        String name,
        String email,
        String password,
        AccountRole role
) {}
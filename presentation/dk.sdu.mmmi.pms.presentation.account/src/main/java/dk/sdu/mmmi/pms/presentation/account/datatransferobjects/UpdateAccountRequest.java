package dk.sdu.mmmi.pms.presentation.account.datatransferobjects;

import dk.sdu.mmmi.pms.core.account.AccountRole;

/**
 * Data transfer object representing a request to update an existing account.
 * This record encapsulates the details required for updating an account,
 * including the name, email, password and role.
 *
 * @param name     the name of the account holder
 * @param email    the email address of the account holder
 * @param password the raw password for the account
 * @param role     the {@link AccountRole} assigned to the account
 */
public record UpdateAccountRequest(
        String name,
        String email,
        String password,
        AccountRole role
) {}
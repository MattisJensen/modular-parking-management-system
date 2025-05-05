package dk.sdu.mmmi.pms.presentation.account.datatransferobject;

import dk.sdu.mmmi.pms.core.account.AccountRole;

import java.util.UUID;

/**
 * Data transfer object representing an account response.
 * This record encapsulates the account's unique identifier, name, email, and role.
 *
 * @param id    the unique {@link UUID} of the account
 * @param name  the name of the account holder
 * @param email the email address of the account holder
 * @param role  the {@link AccountRole} assigned to the account
 */
public record AccountResponse(
        UUID id,
        String name,
        String email,
        AccountRole role
) {}
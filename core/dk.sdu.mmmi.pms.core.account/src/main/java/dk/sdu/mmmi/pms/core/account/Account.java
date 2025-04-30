package dk.sdu.mmmi.pms.core.account;

import java.util.UUID;

/**
 * Represents an account in the system.
 * This record is immutable and contains the account's details.
 *
 * @param id          Unique identifier for the account.
 * @param name        Name of the account holder.
 * @param email       Email address of the account holder.
 * @param password    Password for the account.
 * @param accountRole Role of the account (e.g., USER, ADMIN).
 */
public record Account(
        UUID id,
        String name,
        String email,
        String password,
        AccountRole accountRole
) {}
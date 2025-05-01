package dk.sdu.mmmi.pms.core.account;

import java.util.UUID;

/**
 * Represents an account in the system.
 * This record is immutable and contains the account's details.
 *
 * @param id          Unique identifier for the {@link Account}.
 * @param name        Name of the {@link Account} holder.
 * @param email       Email address of the {@link Account} holder.
 * @param password    Password for the {@link Account}.
 * @param accountRole Role of the {@link Account} (e.g., {@link AccountRole#USER} or {@link AccountRole#ADMIN}).
 */
public record Account(
        UUID id,
        String name,
        String email,
        String password,
        AccountRole accountRole
) {}
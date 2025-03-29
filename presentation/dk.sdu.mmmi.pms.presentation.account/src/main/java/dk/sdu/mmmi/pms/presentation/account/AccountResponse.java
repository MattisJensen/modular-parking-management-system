package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.core.account.AccountRole;

import java.util.UUID;

public record AccountResponse(
        UUID id,
        String name,
        String email,
        AccountRole role
) {}
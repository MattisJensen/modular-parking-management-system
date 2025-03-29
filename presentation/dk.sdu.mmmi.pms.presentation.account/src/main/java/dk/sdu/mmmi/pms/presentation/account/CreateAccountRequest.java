package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.core.account.AccountRole;

public record CreateAccountRequest(
        String name,
        String email,
        String password,
        AccountRole role
) {}
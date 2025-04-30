package dk.sdu.mmmi.pms.presentation.account.datatransferobjects;

import dk.sdu.mmmi.pms.core.account.AccountRole;

public record UpdateAccountRequest(
        String name,
        String email,
        String password,
        AccountRole role
) {}
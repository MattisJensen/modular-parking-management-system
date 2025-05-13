package dk.sdu.mmmi.pms.presentation.account.datatransferobject;

public record LoginRequest(
    String email,
    String password
) {}
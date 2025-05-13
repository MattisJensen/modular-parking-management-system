package dk.sdu.mmmi.pms.application.account;

import dk.sdu.mmmi.pms.core.account.Account;

public interface AccountValidator {
    String verify(Account account);
}

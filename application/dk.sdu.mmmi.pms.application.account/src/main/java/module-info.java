module dk.sdu.mmmi.pms.application.account {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.account;
    requires dk.sdu.mmmi.pms.application.shared;

    // Visibility
    exports dk.sdu.mmmi.pms.application.account;
    exports dk.sdu.mmmi.pms.application.account.usecase;
    exports dk.sdu.mmmi.pms.application.account.uitl;
}
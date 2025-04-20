module dk.sdu.mmmi.pms.presentation.account {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.account;
    requires dk.sdu.mmmi.pms.application.account;
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.web;

    exports dk.sdu.mmmi.pms.presentation.account;
    opens dk.sdu.mmmi.pms.presentation.account to spring.core, spring.beans, spring.context, spring.web;
}
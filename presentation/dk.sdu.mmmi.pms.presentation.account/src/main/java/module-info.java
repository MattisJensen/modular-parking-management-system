module dk.sdu.mmmi.pms.presentation.account {
    requires dk.sdu.mmmi.pms.core.account;
    requires dk.sdu.mmmi.pms.application.account;
    requires spring.web;
    requires java.base;

    // Make Spring able to find the controller
    opens dk.sdu.mmmi.pms.presentation.account to spring.core, spring.beans, spring.web;
    exports dk.sdu.mmmi.pms.presentation.account;
}
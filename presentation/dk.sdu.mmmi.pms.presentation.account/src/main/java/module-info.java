module dk.sdu.mmmi.pms.presentation.account {
    requires dk.sdu.mmmi.pms.core.account;
    requires dk.sdu.mmmi.pms.application.account;
    requires dk.sdu.mmmi.pms.application.shared;

    requires spring.context;
    requires spring.web;

    exports dk.sdu.mmmi.pms.presentation.account;
}
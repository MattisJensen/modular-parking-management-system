module dk.sdu.mmmi.pms.presentation.main {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;

    // Open packages for Spring's component scanning
    opens dk.sdu.mmmi.pms.presentation.main to spring.core, spring.beans, spring.context;

    // Account infrastructure modules
    requires dk.sdu.mmmi.pms.infrastructure.account;
    requires dk.sdu.mmmi.pms.core.account;
    requires dk.sdu.mmmi.pms.application.account;
    requires dk.sdu.mmmi.pms.infrastructure.security;
}
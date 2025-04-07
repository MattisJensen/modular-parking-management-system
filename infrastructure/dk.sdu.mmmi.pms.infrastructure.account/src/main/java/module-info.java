module dk.sdu.mmmi.pms.infrastructure.account {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.account;
    requires dk.sdu.mmmi.pms.application.account;

    // External dependencies
    requires spring.context;
    requires spring.data.jpa;
    requires jakarta.persistence;

    exports dk.sdu.mmmi.pms.infrastructure.account;
}
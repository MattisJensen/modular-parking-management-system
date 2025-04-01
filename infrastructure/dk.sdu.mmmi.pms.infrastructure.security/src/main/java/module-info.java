module dk.sdu.mmmi.pms.infrastructure.security {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.security.crypto;

    exports dk.sdu.mmmi.pms.infrastructure.security;
}
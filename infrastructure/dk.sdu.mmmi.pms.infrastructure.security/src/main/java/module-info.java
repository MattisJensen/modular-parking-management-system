module dk.sdu.mmmi.pms.infrastructure.security {
    requires dk.sdu.mmmi.pms.application.shared;
    requires spring.context;
    requires spring.security.crypto;
    requires spring.security.config;

    exports dk.sdu.mmmi.pms.infrastructure.security;
}
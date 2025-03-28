module dk.sdu.mmmi.pms.infrastructure.security {
    requires dk.sdu.mmmi.pms.application.shared;
    requires spring.context;
    requires spring.security.crypto;

    exports dk.sdu.mmmi.pms.infrastructure.security;
}
module dk.sdu.mmmi.pms.infrastructure.security {
    requires dk.sdu.mmmi.pms.application.shared;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.security.core;
    requires spring.security.config;
    requires spring.security.web;
    requires spring.security.crypto;
//    requires jakarta.servlet;

    exports dk.sdu.mmmi.pms.infrastructure.security;

    opens dk.sdu.mmmi.pms.infrastructure.security to spring.core, spring.beans, spring.context;
}
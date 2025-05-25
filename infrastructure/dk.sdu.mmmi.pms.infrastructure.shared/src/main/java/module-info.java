import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.infrastructure.shared.config.SecurityConfigProvider;

module dk.sdu.mmmi.pms.infrastructure.shared {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.beans;
    requires spring.context;
    requires spring.security.config;
    requires spring.security.core;
    requires spring.security.crypto;
    requires spring.security.web;
    requires spring.web;
    requires jjwt.api;
    requires org.apache.tomcat.embed.core;

    // Visibility
    provides ModuleConfigurationSPI with SecurityConfigProvider;
    opens dk.sdu.mmmi.pms.infrastructure.shared to spring.beans, spring.context, spring.core;
    opens dk.sdu.mmmi.pms.infrastructure.shared.authentication to spring.beans, spring.context, spring.core;
    opens dk.sdu.mmmi.pms.infrastructure.shared.config to spring.beans, spring.context, spring.core;
}
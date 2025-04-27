import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.infrastructure.security.config.SecurityConfigProvider;

module dk.sdu.mmmi.pms.infrastructure.security {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.application.shared;

    provides ModuleConfigurationSPI with SecurityConfigProvider;

    // External dependencies
    requires spring.context;
    requires spring.security.crypto;
    requires spring.beans;

    opens dk.sdu.mmmi.pms.infrastructure.security to spring.beans, spring.context, spring.core, spring.web;
    opens dk.sdu.mmmi.pms.infrastructure.security.config to spring.beans, spring.context, spring.core, spring.web;

//    exports dk.sdu.mmmi.pms.infrastructure.security;
}
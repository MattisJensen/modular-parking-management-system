import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.infrastructure.security.config.SecurityConfigProvider;

module dk.sdu.mmmi.pms.infrastructure.security {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.beans;
    requires spring.context;
    requires spring.security.crypto;

    // Visibility
    provides ModuleConfigurationSPI with SecurityConfigProvider;
    opens dk.sdu.mmmi.pms.infrastructure.security to spring.beans, spring.context, spring.core;
    opens dk.sdu.mmmi.pms.infrastructure.security.config to spring.beans, spring.context, spring.core;
}
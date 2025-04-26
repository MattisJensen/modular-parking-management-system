import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.presentation.account.config.AccountConfigProvider;

module dk.sdu.mmmi.pms.presentation.account {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.account;
    requires dk.sdu.mmmi.pms.application.account;
    requires dk.sdu.mmmi.pms.application.shared;
    provides ModuleConfigurationSPI with AccountConfigProvider;

    // External dependencies
    requires spring.context;
    requires spring.web;

//    exports dk.sdu.mmmi.pms.presentation.account;
    opens dk.sdu.mmmi.pms.presentation.account to spring.core, spring.beans, spring.context, spring.web;
    opens dk.sdu.mmmi.pms.presentation.account.config to spring.beans, spring.context, spring.core, spring.web;
}
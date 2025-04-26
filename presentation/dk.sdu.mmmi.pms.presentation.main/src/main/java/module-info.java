import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

module dk.sdu.mmmi.pms.presentation.main {
    // External dependencies
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;

    requires org.apache.tomcat.embed.core;
    requires jakarta.annotation;

    opens dk.sdu.mmmi.pms.presentation.main to spring.beans, spring.core, spring.context;
    opens dk.sdu.mmmi.pms.presentation.main.config to spring.beans, spring.context, spring.core;
    opens dk.sdu.mmmi.pms.presentation.main.spring to spring.beans, spring.context, spring.core;
    opens dk.sdu.mmmi.pms.presentation.main.tomcat to spring.beans, spring.context, spring.core;

    // Internal dependencies
    requires dk.sdu.mmmi.pms.application.shared;
    uses ModuleConfigurationSPI;
}
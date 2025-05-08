import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

module dk.sdu.mmmi.pms.presentation.main {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.application.shared;
    uses ModuleConfigurationSPI;

    // External dependencies
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;

    requires org.apache.tomcat.embed.core;
    requires jakarta.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;

    // Visibility
    opens dk.sdu.mmmi.pms.presentation.main to spring.beans, spring.context, spring.core;
    opens dk.sdu.mmmi.pms.presentation.main.config to spring.beans, spring.context, spring.core;
}
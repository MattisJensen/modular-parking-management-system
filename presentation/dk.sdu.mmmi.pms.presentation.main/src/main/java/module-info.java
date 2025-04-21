module dk.sdu.mmmi.pms.presentation.main {
    // External dependencies
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;

    requires org.apache.tomcat.embed.core;
    requires jakarta.annotation;

    // Internal dependencies
    requires dk.sdu.mmmi.pms.presentation.account;

    exports dk.sdu.mmmi.pms.presentation.main;
}
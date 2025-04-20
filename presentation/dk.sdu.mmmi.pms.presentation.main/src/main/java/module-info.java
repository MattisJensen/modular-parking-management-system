module dk.sdu.mmmi.pms.presentation.main {
    // External dependencies
//    requires spring.boot;
//    requires spring.boot.autoconfigure;
    requires spring.context;
//    requires spring.data.jpa;
    requires spring.aop;
    requires spring.web;
    requires spring.webmvc;
    requires spring.expression;
    requires org.apache.tomcat.embed.core;
    requires org.apache.commons.logging;
    requires jakarta.annotation;
    requires micrometer.observation;


    // Internal dependencies
    requires dk.sdu.mmmi.pms.presentation.account;

    exports dk.sdu.mmmi.pms.presentation.main;
}
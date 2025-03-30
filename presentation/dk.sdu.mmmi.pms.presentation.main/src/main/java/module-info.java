module dk.sdu.mmmi.pms.presentation.main {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
//    requires spring.data.jpa;

//    requires org.apache.tomcat.embed.core;

//    requires transitive dk.sdu.mmmi.pms.infrastructure.account;
//    requires transitive dk.sdu.mmmi.pms.infrastructure.database;
//    requires transitive dk.sdu.mmmi.pms.infrastructure.security;
    requires transitive dk.sdu.mmmi.pms.presentation.account;

    opens dk.sdu.mmmi.pms.presentation.main to spring.core, spring.beans, spring.context;

    exports dk.sdu.mmmi.pms.presentation.main;
}
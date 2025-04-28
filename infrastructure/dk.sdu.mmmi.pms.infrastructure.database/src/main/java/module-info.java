import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.infrastructure.database.DatabaseConfigProvider;

module dk.sdu.mmmi.pms.infrastructure.database {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.core;
//    requires spring.data.jpa;
//    requires spring.data.commons;
    requires spring.jdbc;
    requires spring.orm;
    requires spring.tx;
    requires java.sql;
//    requires org.postgresql.jdbc;
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires jakarta.xml.bind;
    requires jakarta.activation;
    requires org.jboss.jandex;
//    requires jakarta.interceptor;
    requires org.hibernate.commons.annotations;
    requires org.hibernate.orm.core;
    requires com.fasterxml.classmate;
//    requires com.zaxxer.hikari;
    requires net.bytebuddy;
    requires org.antlr.antlr4.runtime;


    // Visibility
    provides ModuleConfigurationSPI with DatabaseConfigProvider;
    opens dk.sdu.mmmi.pms.infrastructure.database;// to spring.beans, spring.context, spring.core;

}
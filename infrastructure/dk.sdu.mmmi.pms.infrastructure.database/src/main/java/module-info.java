import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.infrastructure.database.DatabaseConfigProvider;

module dk.sdu.mmmi.pms.infrastructure.database {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.core;
    requires spring.jdbc;
    requires spring.orm;
    requires spring.tx;

    requires jakarta.activation;
    requires jakarta.persistence;
    requires jakarta.transaction;
    requires jakarta.xml.bind;

    requires com.fasterxml.classmate;
    requires java.sql;
    requires org.antlr.antlr4.runtime;
    requires org.jboss.jandex;
    requires org.hibernate.commons.annotations;
    requires org.hibernate.orm.core;
    requires net.bytebuddy;

    // Visibility
    provides ModuleConfigurationSPI with DatabaseConfigProvider;
    opens dk.sdu.mmmi.pms.infrastructure.database to spring.beans, spring.context, spring.core;
}
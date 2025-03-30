module dk.sdu.mmmi.pms.infrastructure.database {
    // Internal dependencies

    // External dependencies
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires spring.jdbc;

    requires java.sql;
    requires spring.orm;
    requires spring.tx;
    requires spring.data.commons;
    requires org.postgresql.jdbc;

    requires com.zaxxer.hikari;
    requires org.slf4j;


    exports dk.sdu.mmmi.pms.infrastructure.database;
    opens dk.sdu.mmmi.pms.infrastructure.database to spring.core, spring.beans, spring.context;
}
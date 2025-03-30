module dk.sdu.mmmi.pms.infrastructure.database {
    // Internal dependencies
//    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.boot;
    requires spring.beans;
    requires spring.jdbc;

    requires java.sql;
    requires spring.orm;
    requires spring.tx;
    requires spring.data.commons;

    exports dk.sdu.mmmi.pms.infrastructure.database;
    opens dk.sdu.mmmi.pms.infrastructure.database to spring.core;
}
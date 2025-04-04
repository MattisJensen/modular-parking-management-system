module dk.sdu.mmmi.pms.infrastructure.database {
    // Internal dependencies

    // External dependencies
    requires spring.context;
    requires spring.boot;
    requires spring.beans;

    requires java.sql;
    requires spring.orm;
    requires spring.tx;

    exports dk.sdu.mmmi.pms.infrastructure.database;
}
module dk.sdu.mmmi.pms.infrastructure.database {
    // Internal dependencies
//    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.boot;
    requires spring.beans;

    requires java.sql;
    requires spring.orm;

    exports dk.sdu.mmmi.pms.infrastructure.database;
}
module dk.sdu.mmmi.pms.infrastructure.database {
    // Internal dependencies

    // External dependencies
    requires spring.boot;
    requires spring.context;
    requires spring.orm;

    requires jakarta.persistence;

    exports dk.sdu.mmmi.pms.infrastructure.database;
}
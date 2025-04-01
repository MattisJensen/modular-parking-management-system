module dk.sdu.mmmi.pms.presentation.main {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;

//    requires transitive dk.sdu.mmmi.pms.infrastructure.account;
//    requires transitive dk.sdu.mmmi.pms.infrastructure.database;
//    requires transitive dk.sdu.mmmi.pms.infrastructure.security;
    requires transitive dk.sdu.mmmi.pms.presentation.account;

    exports dk.sdu.mmmi.pms.presentation.main;
}
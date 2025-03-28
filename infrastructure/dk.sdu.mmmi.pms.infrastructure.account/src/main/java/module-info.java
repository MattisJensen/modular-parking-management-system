module dk.sdu.mmmi.pms.infrastructure.account {
    requires dk.sdu.mmmi.pms.core.account;
    requires dk.sdu.mmmi.pms.application.account;
    requires spring.context;
    requires spring.data.jpa;
    requires jakarta.persistence;

    exports dk.sdu.mmmi.pms.infrastructure.account;

    // Open packages for JPA/Spring reflection access
    opens dk.sdu.mmmi.pms.infrastructure.account.jpa to spring.core; //, org.hibernate.orm.core;
}
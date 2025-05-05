import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.infrastructure.parkinglot.config.ParkingLotConfigProvider;

module dk.sdu.mmmi.pms.infrastructure.parkinglot {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.parkinglot;
    requires dk.sdu.mmmi.pms.application.parkinglot;
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires spring.tx;

    // Visibility
    provides ModuleConfigurationSPI with ParkingLotConfigProvider;
    opens dk.sdu.mmmi.pms.infrastructure.parkinglot to spring.beans, spring.context, spring.core, org.hibernate.orm.core;
    opens dk.sdu.mmmi.pms.infrastructure.parkinglot.config to spring.beans, spring.context, spring.core;
    opens dk.sdu.mmmi.pms.infrastructure.parkinglot.persistence to spring.beans, spring.context, spring.core, org.hibernate.orm.core;
}
import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.infrastructure.parkingspot.config.ParkingSpotConfigProvider;

module dk.sdu.mmmi.pms.infrastructure.parkingspot {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.parkingspot;
    requires dk.sdu.mmmi.pms.application.shared;
    requires dk.sdu.mmmi.pms.application.parkingspot;

    // External dependencies
    requires spring.context;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires spring.tx;

    // Visibility
    provides ModuleConfigurationSPI with ParkingSpotConfigProvider;
    opens dk.sdu.mmmi.pms.infrastructure.parkingspot to spring.beans, spring.context, spring.core, org.hibernate.orm.core;
    opens dk.sdu.mmmi.pms.infrastructure.parkingspot.config to spring.beans, spring.context, spring.core;
    opens dk.sdu.mmmi.pms.infrastructure.parkingspot.persistence to spring.beans, spring.context, spring.core, org.hibernate.orm.core;
}
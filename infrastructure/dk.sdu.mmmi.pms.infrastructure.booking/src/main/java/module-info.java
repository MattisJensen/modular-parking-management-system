import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.infrastructure.booking.config.BookingConfigProvider;

module dk.sdu.mmmi.pms.infrastructure.booking {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.booking;
    requires dk.sdu.mmmi.pms.application.booking;
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires spring.tx;
    requires dk.sdu.mmmi.pms.core.parkingspot;
    requires spring.data.commons;

    // Visibility
    provides ModuleConfigurationSPI with BookingConfigProvider;
    opens dk.sdu.mmmi.pms.infrastructure.booking to spring.beans, spring.context, spring.core, org.hibernate.orm.core;
    opens dk.sdu.mmmi.pms.infrastructure.booking.config to spring.beans, spring.context, spring.core;
    opens dk.sdu.mmmi.pms.infrastructure.booking.persistence to spring.beans, spring.context, spring.core, org.hibernate.orm.core;
}

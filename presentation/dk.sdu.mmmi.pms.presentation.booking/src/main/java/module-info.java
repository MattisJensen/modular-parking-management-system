import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.presentation.booking.config.BookingConfigProvider;

module dk.sdu.mmmi.pms.presentation.booking {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.booking;
    requires dk.sdu.mmmi.pms.application.booking;
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.web;

    // Visibility
    provides ModuleConfigurationSPI with BookingConfigProvider;
    opens dk.sdu.mmmi.pms.presentation.booking to spring.beans, spring.context, spring.core, spring.web;
    opens dk.sdu.mmmi.pms.presentation.booking.config to spring.beans, spring.context, spring.core, spring.web;
    exports dk.sdu.mmmi.pms.presentation.booking.datatransferobject to com.fasterxml.jackson.databind;
}
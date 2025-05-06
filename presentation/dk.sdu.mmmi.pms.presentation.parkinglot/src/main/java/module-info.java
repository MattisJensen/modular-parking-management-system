import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.presentation.parkinglot.config.ParkingLotConfigProvider;

module dk.sdu.mmmi.pms.presentation.parkinglot {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.parkinglot;
    requires dk.sdu.mmmi.pms.application.parkinglot;
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.context;
    requires spring.web;

    // Visibility
    provides ModuleConfigurationSPI with ParkingLotConfigProvider;
    opens dk.sdu.mmmi.pms.presentation.parkinglot to spring.beans, spring.context, spring.core, spring.web;
    opens dk.sdu.mmmi.pms.presentation.parkinglot.config to spring.beans, spring.context, spring.core, spring.web;
    exports dk.sdu.mmmi.pms.presentation.parkinglot.datatransferobject to com.fasterxml.jackson.databind;
}
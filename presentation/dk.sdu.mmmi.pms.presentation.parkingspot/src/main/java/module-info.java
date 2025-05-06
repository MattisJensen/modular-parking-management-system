import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import dk.sdu.mmmi.pms.presentation.parkingspot.config.ParkingSpotConfigProvider;

module dk.sdu.mmmi.pms.presentation.parkingspot {
    // Internal dependencies
    requires dk.sdu.mmmi.pms.core.parkingspot;
    requires dk.sdu.mmmi.pms.application.parkinglot;
    requires dk.sdu.mmmi.pms.application.parkingspot;
    requires dk.sdu.mmmi.pms.application.shared;

    // External dependencies
    requires spring.boot.autoconfigure;
    requires spring.core;
    requires spring.context;
    requires spring.web;

    // Visibility
    provides ModuleConfigurationSPI with ParkingSpotConfigProvider;
    opens dk.sdu.mmmi.pms.presentation.parkingspot to spring.beans, spring.context, spring.core, spring.web;
    opens dk.sdu.mmmi.pms.presentation.parkingspot.config to spring.beans, spring.context, spring.core, spring.web;
    exports dk.sdu.mmmi.pms.presentation.parkingspot.datatransferobject to com.fasterxml.jackson.databind;
}
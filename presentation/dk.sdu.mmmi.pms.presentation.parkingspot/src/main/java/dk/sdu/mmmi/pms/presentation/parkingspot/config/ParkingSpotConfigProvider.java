package dk.sdu.mmmi.pms.presentation.parkingspot.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

public class ParkingSpotConfigProvider implements ModuleConfigurationSPI {
    @Override
    public Class<?> getConfigurationClass() {
        return ParkingSpotConfigPresentation.class;
    }
}

package dk.sdu.mmmi.pms.presentation.parkinglot.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

public class ParkingLotConfigProvider implements ModuleConfigurationSPI {
    @Override
    public Class<?> getConfigurationClass() {
        return ParkingLotConfigPresentation.class;
    }
}

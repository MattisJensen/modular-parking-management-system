package dk.sdu.mmmi.pms.infrastructure.parkinglot.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

/**
 * Provides the configuration class for the account infrastructure module.
 * This class implements the {@link ModuleConfigurationSPI} to supply
 * the {@link ParkingLotConfigInfrastructure} configuration.
 */
public class ParkingLotConfigProvider implements ModuleConfigurationSPI {

    /**
     * Returns the configuration class for the account infrastructure module.
     *
     * @return the {@link ParkingLotConfigInfrastructure} class
     */
    @Override
    public Class<?> getConfigurationClass() {
        return ParkingLotConfigInfrastructure.class;
    }
}
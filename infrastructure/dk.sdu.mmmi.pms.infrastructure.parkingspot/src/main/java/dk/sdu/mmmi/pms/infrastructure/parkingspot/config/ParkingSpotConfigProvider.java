package dk.sdu.mmmi.pms.infrastructure.parkingspot.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

/**
 * Provides the configuration class for the parkingspot infrastructure module.
 * This class implements the {@link ModuleConfigurationSPI} to supply
 * the {@link ParkingSpotConfigInfrastructure} configuration.
 */
public class ParkingSpotConfigProvider implements ModuleConfigurationSPI {

    /**
     * Returns the configuration class for the account infrastructure module.
     *
     * @return the {@link ParkingSpotConfigInfrastructure} class
     */
    @Override
    public Class<?> getConfigurationClass() {
        return ParkingSpotConfigInfrastructure.class;
    }
}
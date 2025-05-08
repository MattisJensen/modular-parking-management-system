package dk.sdu.mmmi.pms.infrastructure.booking.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

/**
 * Provides the configuration class for the booking infrastructure module.
 * This class implements the {@link ModuleConfigurationSPI} to supply
 * the {@link BookingConfigInfrastructure} configuration.
 */
public class BookingConfigProvider implements ModuleConfigurationSPI {

    /**
     * Returns the configuration class for the booking infrastructure module.
     *
     * @return the {@link BookingConfigInfrastructure} class
     */
    @Override
    public Class<?> getConfigurationClass() {
        return BookingConfigInfrastructure.class;
    }
}
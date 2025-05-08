package dk.sdu.mmmi.pms.presentation.booking.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

/**
 * Provides the configuration class for the booking presentation module.
 * This class implements the {@link ModuleConfigurationSPI} to supply
 * the {@link BookingConfigPresentation} configuration.
 */
public class BookingConfigProvider implements ModuleConfigurationSPI {

    /**
     * Returns the configuration class for the Booking presentation module.
     *
     * @return the {@link BookingConfigPresentation} class
     */
    @Override
    public Class<?> getConfigurationClass() {
        return BookingConfigPresentation.class;
    }
}

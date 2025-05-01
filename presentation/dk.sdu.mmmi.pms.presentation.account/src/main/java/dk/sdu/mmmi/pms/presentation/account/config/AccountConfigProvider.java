package dk.sdu.mmmi.pms.presentation.account.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

/**
 * Provides the configuration class for the account presentation module.
 * This class implements the {@link ModuleConfigurationSPI} to supply
 * the {@link AccountConfigPresentation} configuration.
 */
public class AccountConfigProvider implements ModuleConfigurationSPI {

    /**
     * Returns the configuration class for the account presentation module.
     *
     * @return the {@link AccountConfigPresentation} class
     */
    @Override
    public Class<?> getConfigurationClass() {
        return AccountConfigPresentation.class;
    }
}

package dk.sdu.mmmi.pms.infrastructure.account.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

/**
 * Provides the configuration class for the account infrastructure module.
 * This class implements the {@link ModuleConfigurationSPI} to supply
 * the {@link AccountConfigInfrastructure} configuration.
 */
public class AccountConfigProvider implements ModuleConfigurationSPI {

    /**
     * Returns the configuration class for the account infrastructure module.
     *
     * @return the {@link AccountConfigInfrastructure} class
     */
    @Override
    public Class<?> getConfigurationClass() {
        return AccountConfigInfrastructure.class;
    }
}
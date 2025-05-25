package dk.sdu.mmmi.pms.infrastructure.shared.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

/**
 * Provides the configuration class for the shared infrastructure module.
 * This class implements the {@link ModuleConfigurationSPI} to supply
 * the {@link SecurityConfigInfrastructure} configuration.
 */
public class SecurityConfigProvider implements ModuleConfigurationSPI {

    /**
     * Returns the configuration class for the shared infrastructure module.
     *
     * @return the {@link SecurityConfigInfrastructure} class
     */
    @Override
    public Class<?> getConfigurationClass() {
        return SecurityConfigInfrastructure.class;
    }
}

package dk.sdu.mmmi.pms.infrastructure.database;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

/**
 * Provides the configuration class for the database infrastructure module.
 * This class implements the {@link ModuleConfigurationSPI} to supply
 * the {@link DatabaseConfigInfrastructure} configuration.
 */
public class DatabaseConfigProvider implements ModuleConfigurationSPI {

    /**
     * Returns the configuration class for the database infrastructure module.
     *
     * @return the {@link DatabaseConfigInfrastructure} class
     */
    @Override
    public Class<?> getConfigurationClass() {
        return DatabaseConfigInfrastructure.class;
    }
}

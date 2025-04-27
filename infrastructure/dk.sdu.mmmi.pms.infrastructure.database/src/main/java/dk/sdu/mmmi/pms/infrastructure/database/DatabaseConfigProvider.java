package dk.sdu.mmmi.pms.infrastructure.database;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

public class DatabaseConfigProvider implements ModuleConfigurationSPI {
    @Override
    public Class<?> getConfigurationClass() {
        return DatabaseConfigInfrastructure.class;
    }
}

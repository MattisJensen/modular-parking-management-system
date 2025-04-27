package dk.sdu.mmmi.pms.infrastructure.account.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

public class AccountConfigProvider implements ModuleConfigurationSPI {
    @Override
    public Class<?> getConfigurationClass() {
        return AccountConfigInfrastructure.class;
    }
}

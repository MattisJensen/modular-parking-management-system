package dk.sdu.mmmi.pms.presentation.account.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

public class AccountConfigProvider implements ModuleConfigurationSPI {
    @Override
    public Class<?> getConfigurationClass() {
        return AccountConfig.class;
    }
}

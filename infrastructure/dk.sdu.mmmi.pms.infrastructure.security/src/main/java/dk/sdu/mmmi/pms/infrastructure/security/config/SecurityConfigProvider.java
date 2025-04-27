package dk.sdu.mmmi.pms.infrastructure.security.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;

public class SecurityConfigProvider implements ModuleConfigurationSPI {
    @Override
    public Class<?> getConfigurationClass() {
        return SecurityConfig.class;
    }
}

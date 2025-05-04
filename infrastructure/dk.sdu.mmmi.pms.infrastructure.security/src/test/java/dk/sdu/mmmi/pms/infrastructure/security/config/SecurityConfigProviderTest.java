package dk.sdu.mmmi.pms.infrastructure.security.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SecurityConfigProviderTest {
    @Test
    void getConfigurationClass_ReturnsSecurityConfigInfrastructure() {
        // Arrange
        ModuleConfigurationSPI configProvider = new SecurityConfigProvider();

        Class<?> configClass = configProvider.getConfigurationClass();

        // Ensure the correct configuration class is returned
        assertEquals(SecurityConfigInfrastructure.class, configClass);
    }
}
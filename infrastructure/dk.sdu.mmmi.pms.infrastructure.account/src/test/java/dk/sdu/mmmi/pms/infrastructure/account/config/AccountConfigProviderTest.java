package dk.sdu.mmmi.pms.infrastructure.account.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountConfigProviderTest {

    @Test
    void getConfigurationClass_ReturnsAccountConfigInfrastructure() {
        // Arrange
        ModuleConfigurationSPI configProvider = new AccountConfigProvider();

        Class<?> configClass = configProvider.getConfigurationClass();

        // Ensure the correct configuration class is returned
        assertEquals(AccountConfigInfrastructure.class, configClass);
    }
}
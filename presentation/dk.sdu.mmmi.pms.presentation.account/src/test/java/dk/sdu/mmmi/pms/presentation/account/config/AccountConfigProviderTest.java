package dk.sdu.mmmi.pms.presentation.account.config;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AccountConfigProviderTest {
    @Test
    void getConfigurationClass_ReturnsDatabaseConfigInfrastructure() {
        // Arrange
        ModuleConfigurationSPI configProvider = new AccountConfigProvider();

        Class<?> configClass = configProvider.getConfigurationClass();

        // Ensure the correct configuration class is returned
        assertEquals(AccountConfigPresentation.class, configClass);
    }
}
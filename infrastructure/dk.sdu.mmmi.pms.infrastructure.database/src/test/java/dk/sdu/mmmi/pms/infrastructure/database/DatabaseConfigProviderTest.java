package dk.sdu.mmmi.pms.infrastructure.database;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DatabaseConfigProviderTest {
    @Test
    void getConfigurationClass_ReturnsDatabaseConfigInfrastructure() {
        // Arrange
        ModuleConfigurationSPI configProvider = new DatabaseConfigProvider();

        Class<?> configClass = configProvider.getConfigurationClass();

        // Ensure the correct configuration class is returned
        assertEquals(DatabaseConfigInfrastructure.class, configClass);
    }
  
}
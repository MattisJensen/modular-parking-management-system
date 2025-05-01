package dk.sdu.mmmi.pms.application.shared;

/**
 * Service Provider Interface (SPI) for module configuration.
 * This interface retrieves the configuration class
 * for a specific module.
 */
public interface ModuleConfigurationSPI {

    /**
     * Retrieves the configuration class for the module.
     *
     * @return the {@link Class} object representing the configuration class
     */
    Class<?> getConfigurationClass();
}
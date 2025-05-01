package dk.sdu.mmmi.pms.presentation.main.spring;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.ServiceLoader;

/**
 * Utility class for initializing and configuring a Spring application context.
 * This class provides methods to create a new {@link AnnotationConfigWebApplicationContext}
 * and register module configurations using {@link ModuleConfigurationSPI}.
 */
public class SpringContextInitializer {

    /**
     * Creates and configures a new {@link AnnotationConfigWebApplicationContext} using the given primary configuration class.
     * The method registers the primary configuration class and any additional module configurations.
     *
     * @param primaryConfig the primary Spring configuration class
     * @return a configured {@link AnnotationConfigWebApplicationContext} instance
     */
    public static AnnotationConfigWebApplicationContext createContext(Class<?> primaryConfig) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(primaryConfig);
        registerModuleConfigurations(context);
        return context;
    }

    /**
     * Registers module configurations from all implementations of {@link ModuleConfigurationSPI}.
     * This method uses the {@link ServiceLoader} to discover and register module configurations
     * to the provided {@link AnnotationConfigWebApplicationContext}.
     *
     * @param context the {@link AnnotationConfigWebApplicationContext} to which module configurations will be registered
     */
    private static void registerModuleConfigurations(AnnotationConfigWebApplicationContext context) {
        ServiceLoader.load(ModuleConfigurationSPI.class)
                .forEach(provider -> context.register(provider.getConfigurationClass()));
    }
}

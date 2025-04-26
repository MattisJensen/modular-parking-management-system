package dk.sdu.mmmi.pms.presentation.main.spring;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.ServiceLoader;

public class SpringContextInitializer {
    /**
     * Creates and configures a new Spring context using the given primary configuration class.
     *
     * @param primaryConfig the primary spring configuration class
     * @return a configured AnnotationConfigWebApplicationContext instance
     */
    public static AnnotationConfigWebApplicationContext createContext(Class<?> primaryConfig) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(primaryConfig);
        registerModuleConfigurations(context);
        return context;
    }

    /**
     * Registers module configurations from all implementations of ModuleConfigurationSPI.
     * This method uses the ServiceLoader to discover and register module configurations.
     * The discovered configurations are registered to the provided Spring context.
     *
     * @param context the context to which module configurations will be registered
     */
    private static void registerModuleConfigurations(AnnotationConfigWebApplicationContext context) {
        ServiceLoader.load(ModuleConfigurationSPI.class)
                .forEach(provider -> context.register(provider.getConfigurationClass()));
    }
}

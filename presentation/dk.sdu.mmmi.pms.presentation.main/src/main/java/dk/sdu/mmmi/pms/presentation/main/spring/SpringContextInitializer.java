package dk.sdu.mmmi.pms.presentation.main.spring;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.ServiceLoader;

public class SpringContextInitializer {
    public static AnnotationConfigWebApplicationContext createContext(Class<?> primaryConfig) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(primaryConfig);
        loadModuleConfigurations(context);
        return context;
    }

    private static void loadModuleConfigurations(AnnotationConfigWebApplicationContext context) {
        ServiceLoader.load(ModuleConfigurationSPI.class)
                .forEach(provider -> context.register(provider.getConfigurationClass()));
    }
}

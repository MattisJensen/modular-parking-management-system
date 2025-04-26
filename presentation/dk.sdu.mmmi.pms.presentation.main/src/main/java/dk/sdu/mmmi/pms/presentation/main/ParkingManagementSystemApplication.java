package dk.sdu.mmmi.pms.presentation.main;

import dk.sdu.mmmi.pms.application.shared.ModuleConfigurationSPI;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.ServiceLoader;

public class ParkingManagementSystemApplication {
    public static void main(String[] args) throws Exception {
        // Initialize embedded Tomcat server
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        // Create a root context for the Tomcat server
        Context tomcatContext = tomcat.addContext("", null);

        // Register and configure the Spring context
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(AppConfig.class);

        ServiceLoader<ModuleConfigurationSPI> moduleConfigProviders = ServiceLoader.load(ModuleConfigurationSPI.class);
        moduleConfigProviders.forEach(moduleConfigProvider -> springContext.register(moduleConfigProvider.getConfigurationClass()));

        // Create and add the DispatcherServlet to handle requests
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);
        Tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet).setLoadOnStartup(1);
        tomcatContext.addServletMappingDecoded("/*", "dispatcherServlet");

        // Start Tomcat and display the package specific beans
        tomcat.start();
        BeanPrinter.printPackageSpecificBeans(springContext, "dk.sdu.mmmi.pms");
        tomcat.getServer().await();
    }
}
package dk.sdu.mmmi.pms.presentation.main;

import dk.sdu.mmmi.pms.presentation.main.config.AppConfig;
import dk.sdu.mmmi.pms.presentation.main.spring.BeanPrinter;
import dk.sdu.mmmi.pms.presentation.main.spring.SpringContextInitializer;
import dk.sdu.mmmi.pms.presentation.main.tomcat.ServletRegistrar;
import dk.sdu.mmmi.pms.presentation.main.tomcat.TomcatServerFactory;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Main application class for the Parking Management System.
 */
public class ParkingManagementSystemApplication {
    private static final int PORT = 8080;
    private static final String SERVLET_NAME = "dispatcherServlet";

    /**
     * Entry point for the Parking Management System application.
     * This method creates and configures the {@link Tomcat} server, initializes the Spring context
     * and starts the server.
     *
     * @param args command-line arguments
     * @throws Exception if an error occurs during server startup
     */
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = TomcatServerFactory.createServer(PORT);
        Context tomcatContext = tomcat.addContext("", null);

        AnnotationConfigWebApplicationContext springContext = SpringContextInitializer.createContext(AppConfig.class);

        ServletRegistrar.registerDispatcherServlet(tomcatContext, springContext, SERVLET_NAME);

        startServer(tomcat, springContext);
    }

    /**
     * Starts the {@link Tomcat} server and waits for it to finish.
     * This method also uses {@link BeanPrinter} to print beans from the Spring context.
     *
     * @param tomcat  the {@link Tomcat} server instance
     * @param context the {@link AnnotationConfigWebApplicationContext} containing the Spring application context
     * @throws Exception if an error occurs while starting the server
     */
    private static void startServer(Tomcat tomcat, AnnotationConfigWebApplicationContext context) throws Exception {
        tomcat.start();
        BeanPrinter.printPackageSpecificBeans(context, "dk.sdu.mmmi.pms");
        tomcat.getServer().await();
    }
}
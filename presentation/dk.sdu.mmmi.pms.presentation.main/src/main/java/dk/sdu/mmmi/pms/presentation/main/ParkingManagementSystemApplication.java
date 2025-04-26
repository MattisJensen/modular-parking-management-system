package dk.sdu.mmmi.pms.presentation.main;

import dk.sdu.mmmi.pms.presentation.main.config.AppConfig;
import dk.sdu.mmmi.pms.presentation.main.spring.BeanPrinter;
import dk.sdu.mmmi.pms.presentation.main.spring.SpringContextInitializer;
import dk.sdu.mmmi.pms.presentation.main.tomcat.ServletRegistrar;
import dk.sdu.mmmi.pms.presentation.main.tomcat.TomcatServerFactory;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class ParkingManagementSystemApplication {
    private static final int PORT = 8080;
    private static final String SERVLET_NAME = "dispatcherServlet";

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = TomcatServerFactory.createServer(PORT);
        Context tomcatContext = tomcat.addContext("", null);

        AnnotationConfigWebApplicationContext springContext = SpringContextInitializer.createContext(AppConfig.class);

        ServletRegistrar.registerDispatcherServlet(tomcatContext, springContext, SERVLET_NAME);

        startServer(tomcat, springContext);
    }

    /**
     * Starts the Tomcat server and waits for it to finish.
     *
     * @param tomcat  The Tomcat server instance.
     * @param context The Spring application context.
     * @throws Exception If an error occurs while starting the server.
     */
    private static void startServer(Tomcat tomcat, AnnotationConfigWebApplicationContext context) throws Exception {
        tomcat.start();
        BeanPrinter.printPackageSpecificBeans(context, "dk.sdu.mmmi.pms");
        tomcat.getServer().await();
    }
}
package dk.sdu.mmmi.pms.presentation.main;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ParkingManagementSystemApplication {
    public static void main(String[] args) throws Exception {
        // Initialize embedded Tomcat server
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        // Create a root context for the Tomcat server
        Context context = tomcat.addContext("", null);

        // Register and configure the Spring context
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);

        // Create and add the DispatcherServlet to handle requests
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet).setLoadOnStartup(1);
        context.addServletMappingDecoded("/*", "dispatcherServlet");

        // Start Tomcat and display the package specific beans
        tomcat.start();
        BeanPrinter.printPackageSpecificBeans(appContext, "dk.sdu.mmmi.pms");
        tomcat.getServer().await();
    }
}
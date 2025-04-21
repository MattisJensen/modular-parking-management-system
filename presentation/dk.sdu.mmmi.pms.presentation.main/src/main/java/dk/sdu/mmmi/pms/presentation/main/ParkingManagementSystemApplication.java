package dk.sdu.mmmi.pms.presentation.main;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Arrays;

public class ParkingManagementSystemApplication {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context context = tomcat.addContext("", null);

        // Create and configure Spring DispatcherServlet
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet).setLoadOnStartup(1);
        context.addServletMappingDecoded("/*", "dispatcherServlet");

        tomcat.start();
        BeanPrinter.printPackageSpecificBeans(appContext, "dk.sdu.mmmi.pms");
        tomcat.getServer().await();
    }
}
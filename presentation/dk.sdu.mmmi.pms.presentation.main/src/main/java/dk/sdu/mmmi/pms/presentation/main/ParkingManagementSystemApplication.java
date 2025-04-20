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

        Context context = tomcat.addContext("", null);

        // Create and configure Spring DispatcherServlet
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet).setLoadOnStartup(1);
        context.addServletMappingDecoded("/*", "dispatcherServlet");

        tomcat.start();
        printPmsSpecificBeans(appContext);
        tomcat.getServer().await();
    }

    public static void printPmsSpecificBeans(ApplicationContext context) {
        System.out.println("\n=== Beans of PMS Modules ===");
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        int pmsBeanCount = 0;

        for (String beanName : beanNames) {
            try {
                Class<?> type = context.getType(beanName);
                if (type != null && type.getName().startsWith("dk.sdu.mmmi.pms")) {
                    System.out.println(beanName + " - " + type.getName());
                    pmsBeanCount++;
                }
            } catch (Exception e) {
                System.out.println("Error getting type for bean: " + beanName);
            }
        }
    }
}
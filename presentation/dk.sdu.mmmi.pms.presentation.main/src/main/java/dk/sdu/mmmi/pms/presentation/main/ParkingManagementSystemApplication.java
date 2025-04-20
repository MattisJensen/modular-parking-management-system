package dk.sdu.mmmi.pms.presentation.main;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

public class ParkingManagementSystemApplication {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Create a temporary directory for Tomcat's docBase
        File docBase = Files.createTempDirectory("tomcat").toFile();
        docBase.deleteOnExit();

        // Add a context with empty path and valid docBase
        Context context = tomcat.addContext("", docBase.getAbsolutePath());

        // Initialize Spring context
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);
        appContext.refresh();

        // Add DispatcherServlet to Tomcat context
        DispatcherServlet servlet = new DispatcherServlet(appContext);
        Tomcat.addServlet(context, "dispatcher", servlet);
        context.addServletMappingDecoded("/api/*", "dispatcher");

        tomcat.start();
        tomcat.getServer().await();

        printPmsSpecificBeans(appContext);
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

//@SpringBootApplication
//@EnableJpaRepositories(basePackages = "dk.sdu.mmmi.pms.infrastructure")
//@ComponentScan(basePackages = "dk.sdu.mmmi.pms")
//public class ParkingManagementSystemApplication {
//    public static void main(String[] args) {
//        System.out.println("Starting Parking Management System Application");
//        ApplicationContext context = SpringApplication.run(ParkingManagementSystemApplication.class, args);
//
//        printPmsSpecificBeans(context);
//    }
//
//    public static void printPmsSpecificBeans(ApplicationContext context) {
//        System.out.println("\n=== Beans of PMS Modules ===");
//        String[] beanNames = context.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        int pmsBeanCount = 0;
//
//        for (String beanName : beanNames) {
//            try {
//                Class<?> type = context.getType(beanName);
//                if (type != null && type.getName().startsWith("dk.sdu.mmmi.pms")) {
//                    System.out.println(beanName + " - " + type.getName());
//                    pmsBeanCount++;
//                }
//            } catch (Exception e) {
//                System.out.println("Error getting type for bean: " + beanName);
//            }
//        }
//
//        System.out.println("Total PMS specific beans: " + pmsBeanCount);
//    }
//}
package dk.sdu.mmmi.pms.presentation.main.tomcat;

import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.apache.catalina.Context;

public class ServletRegistrar {
    public static void registerDispatcherServlet(Context tomcatContext, AnnotationConfigWebApplicationContext springContext, String servletName) {
        DispatcherServlet servlet = new DispatcherServlet(springContext);
        Tomcat.addServlet(tomcatContext, servletName, servlet)
                .setLoadOnStartup(1);
        tomcatContext.addServletMappingDecoded("/*", servletName);
    }
}

package dk.sdu.mmmi.pms.presentation.main.tomcat;

import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.apache.catalina.Context;

/**
 * Handles registration of a Spring MVC {@link DispatcherServlet} within an embedded {@link Tomcat} container.
 * This class centralizes the servlet setup and ensures that incoming requests are routed through
 * the registered Spring context.
 */
public class ServletRegistrar {

    /**
     * Registers a Spring {@link DispatcherServlet} with the provided {@link Context}.
     * The servlet is configured to handle all `/*` requests at startup.
     *
     * @param tomcatContext the {@link Context} in which to add the servlet
     * @param springContext the {@link AnnotationConfigWebApplicationContext} to be used by the servlet
     * @param servletName   the unique name under which the servlet is registered
     */
    public static void registerDispatcherServlet(Context tomcatContext, AnnotationConfigWebApplicationContext springContext, String servletName) {
        DispatcherServlet servlet = new DispatcherServlet(springContext);
        Tomcat.addServlet(tomcatContext, servletName, servlet)
                .setLoadOnStartup(1);
        tomcatContext.addServletMappingDecoded("/*", servletName);
    }
}
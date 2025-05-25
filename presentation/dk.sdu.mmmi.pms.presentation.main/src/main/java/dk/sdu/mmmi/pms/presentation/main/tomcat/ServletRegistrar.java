package dk.sdu.mmmi.pms.presentation.main.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

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
        // Allow Spring to handle shared filters
        tomcatContext.addFilterDef(createSpringSecurityFilterDef("springSecurityFilterChain"));
        tomcatContext.addFilterMap(createSpringSecurityFilterMap("springSecurityFilterChain"));

        // Register the DispatcherServlet with the Spring context
        DispatcherServlet servlet = new DispatcherServlet(springContext);
        Tomcat.addServlet(tomcatContext, servletName, servlet)
                .setLoadOnStartup(2);
        tomcatContext.addServletMappingDecoded("/*", servletName);
    }

    /**
     * Creates a {@link FilterDef} for the Spring Security filter chain.
     * This filter is responsible for delegating shared-related tasks to the Spring context.
     *
     * @param filterName the name of the filter
     * @return a configured {@link FilterDef} instance
     */
    private static FilterDef createSpringSecurityFilterDef(String filterName) {
        FilterDef filterDef = new FilterDef();
        filterDef.setFilterName(filterName);
        filterDef.setFilterClass(DelegatingFilterProxy.class.getName());
        filterDef.addInitParameter("targetBeanName", filterName);
        return filterDef;
    }

    /**
     * Creates a {@link FilterMap} that maps the Spring Security filter to all URL patterns.
     * This ensures that the filter is applied to all incoming requests.
     *
     * @param filterName the name of the filter
     * @return a configured {@link FilterMap} instance
     */
    private static FilterMap createSpringSecurityFilterMap(String filterName) {
        // Map filter to all URLs
        FilterMap filterMap = new FilterMap();
        filterMap.setFilterName(filterName);
        filterMap.addURLPattern("/*");
        return filterMap;
    }
}
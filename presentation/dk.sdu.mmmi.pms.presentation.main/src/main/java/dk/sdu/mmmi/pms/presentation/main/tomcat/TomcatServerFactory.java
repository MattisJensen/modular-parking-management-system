package dk.sdu.mmmi.pms.presentation.main.tomcat;

import org.apache.catalina.startup.Tomcat;

/**
 * Factory class for creating and configuring {@link Tomcat} server instances.
 * This class provides a method to initialize a new {@link Tomcat} server with a specified port.
 */
public class TomcatServerFactory {

    /**
     * Creates a new {@link Tomcat} server instance, sets its port and initializes its connector.
     *
     * @param port the port on which the {@link Tomcat} server will listen
     * @return the configured {@link Tomcat} server instance
     */
    public static Tomcat createServer(int port) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();
        return tomcat;
    }
}
package dk.sdu.mmmi.pms.presentation.main.tomcat;

import org.apache.catalina.startup.Tomcat;

public class TomcatServerFactory {

    /**
     * Creates a new Tomcat server instance, sets its port and initializes its connector.
     *
     * @param port the port on which the Tomcat server will listen
     * @return the configured Tomcat server instance
     */
    public static Tomcat createServer(int port) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();
        return tomcat;
    }
}

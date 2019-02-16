import com.sun.net.httpserver.HttpServer;
import handlers.LoginHandler;
import handlers.RegisterHandler;
import handlers.RootHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    /**
     * The maximum number of waiting incoming connections to queue.
     */
    private static final int MAX_WAITING_CONNECTIONS = 12;

    /**
     * Java provides an HttpServer class that can be used to embed
     *      an HTTP server in any Java program.
     *      Using the HttpServer class, we can make a Java
     *      program that can receive incoming HTTP requests and respond
     *      with appropriate HTTP responses.
     *      HttpServer is the class that actually implements the HTTP network
     *      protocol.
     *      The "server" field contains the HttpServer instance for this program,
     *      which is initialized in the "run" method below.
     */
    private HttpServer server;

    /**
     * This method initializes and runs the server.
     * The "portNumber" parameter specifies the port number on which the
     * server should accept incoming client connections.
     */
    public void run(String portNumber) {
        System.out.printf("Initializing HTTP Server on port %s", portNumber);
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //Default executor
        server.setExecutor(null);

        /*
          Log message indicating that the server is creating and installing
               its HTTP handlers.
               The HttpServer class listens for incoming HTTP requests.  When one
               is received, it looks at the URL path inside the HTTP request, and
               forwards the request to the handler for that URL path.
         */
        System.out.println("Creating contexts");

        /*
           Create and install the HTTP handler for the given URL path.
                   When the HttpServer receives an HTTP request containing the
                   specified URL path, it will forward the request to the corresponding
                   handler class for processing.
         */
        server.createContext("/", new RootHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());

        System.out.println("Starting server");
        server.start();
        System.out.println("Server started with no errors");
    }


}

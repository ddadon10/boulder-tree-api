package sh.david.bouldertreeapi;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.io.IOException;
import java.net.URI;
import sh.david.bouldertreeapi.datastore.DataStore;

/**
 * Main class.
 */
public class Main {

  // Base URI the Grizzly HTTP server will listen on
  public static final String BASE_URI = "http://localhost:8000/myapp/";
  public static DataStore DATASTORE;


  static {
    try {
      DATASTORE = new DataStore();
    } catch (FileNotFoundException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  /**
   * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
   *
   * @return Grizzly HTTP server.
   */
  public static HttpServer startServer() {
    // create a resource config that scans for JAX-RS resources and providers
    // in sh.david.bouldertreeapi package

    final ResourceConfig rc = new ResourceConfig().packages("sh.david.bouldertreeapi");

    // create and start a new instance of grizzly http server
    // exposing the Jersey application at BASE_URI
    return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
  }

  /**
   * Main method.
   *
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    final HttpServer server = startServer();
    System.out.println(String.format("Jersey app started with WADL available at "
        + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
    System.in.read();
    server.stop();
  }
}


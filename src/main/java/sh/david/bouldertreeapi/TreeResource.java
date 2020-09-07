package sh.david.bouldertreeapi;

import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sh.david.bouldertreeapi.datastore.Tree;

@Path("trees")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TreeResource {

  @GET @Path("/")
  public Response getTrees() {
    Set<Tree> trees = Main.DATASTORE.getTreeSet();
    Tree tree = trees.iterator().next();
    return Response.ok().entity(tree).build();
  }


}

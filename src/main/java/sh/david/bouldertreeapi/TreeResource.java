package sh.david.bouldertreeapi;

import java.util.TreeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sh.david.bouldertreeapi.datastore.Tree;

@Path("/trees")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TreeResource {

  TreeMap<Integer, Tree> trees = Main.DATASTORE.getTreeDatabase();

  @GET
  @Path("/")
  public Response getTrees() {
    Tree[] arrayTrees = trees.values().toArray(new Tree[0]);
    return Response.ok().entity(arrayTrees).build();
  }

  @GET
  @Path("/{id}")
  public Response getTreeById(@PathParam("id") int id) {
    Tree tree = trees.get(id);
    if (tree == null) {
      return Response.status(404).build();
    }
    return Response.ok().entity(tree).build();

  }


}

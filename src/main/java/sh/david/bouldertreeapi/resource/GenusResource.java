package sh.david.bouldertreeapi.resource;

import java.util.TreeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sh.david.bouldertreeapi.Main;
import sh.david.bouldertreeapi.datastore.Genus;

@Path("/genus")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GenusResource {
  TreeMap<String, Genus> genus = Main.DATASTORE.getGenusDatabase();

  @GET
  @Path("/")
  public Response getGenus(){
  Genus[] genusArray = genus.values().toArray(new Genus[0]);
  return Response.ok().entity(genusArray).build();
  }

  @GET
  @Path("/{name}")
  public Response getGenus(@PathParam("name") String name){
    Genus genusByName = genus.get(name);
    if (genusByName == null) {
      return Response.status(404).build();
    }
    return Response.ok().entity(genusByName).build();
  }

}

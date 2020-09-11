package sh.david.bouldertreeapi.resource;


import java.util.TreeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sh.david.bouldertreeapi.Main;
import sh.david.bouldertreeapi.datastore.Species;

@Path("/species")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SpeciesResource {
  TreeMap<String, Species> species = Main.DATASTORE.getSpeciesDatabase();

  @GET
  @Path("/")
  public Response getSpecies() {
  Species[] speciesArray = species.values().toArray(new Species[0]);
  return Response.ok().entity(speciesArray).build();
  }

  @GET
  @Path("/{code}")
  public Response getSpeciesByCode(@PathParam("code") String code) {
    Species speciesByCode = species.get(code);
    if (speciesByCode == null) {
      return Response.status(404).build();
    }
    return Response.ok().entity(speciesByCode).build();
  }
}

package sh.david.bouldertreeapi.resource;


import java.util.TreeMap;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sh.david.bouldertreeapi.Main;
import sh.david.bouldertreeapi.datastore.Species;
import sh.david.bouldertreeapi.utils.PaginatedEntity;

@Path("/species")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SpeciesResource {

  TreeMap<String, Species> species = Main.DATASTORE.getSpeciesDatabase();

  @GET
  @Path("/")
  public Response getSpecies(
      @DefaultValue("-1") @QueryParam("maxSize") int maxSize,
      @DefaultValue("1") @QueryParam("page") int page) {
    PaginatedEntity<Species> paginatedEntity = new PaginatedEntity<>(
        species.values().toArray(new Species[0]), maxSize, page);

    return Response.ok().entity(paginatedEntity).build();
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

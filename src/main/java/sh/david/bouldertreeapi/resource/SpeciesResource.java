package sh.david.bouldertreeapi.resource;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import sh.david.bouldertreeapi.Main;
import sh.david.bouldertreeapi.datastore.Species;
import sh.david.bouldertreeapi.response.SpeciesResponse;

@Path("/species")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SpeciesResource {

  TreeMap<String, Species> speciesDb = Main.DATASTORE.getSpeciesDatabase();

  @GET
  @Path("/")
  public Response getSpecies(
      @Context UriInfo uriInfo,
      @DefaultValue("-1") @QueryParam("maxSize") int maxSize,
      @DefaultValue("1") @QueryParam("page") int page,
      @QueryParam("code") List<String> code,
      @QueryParam("species") List<String> speciesProperty,
      @QueryParam("name") List<String> name
  ) {

    List<Species> speciesList = new ArrayList<>(speciesDb.values());
    List<Species> filteredSpecies = new ArrayList<>();

    if (Main.SPECIAL_QUERYPARAMS.containsAll(uriInfo.getQueryParameters().keySet())) {
      filteredSpecies = speciesList;
    } else {
      for (Species species : speciesList) {
        if (!code.isEmpty() && !code.contains(species.getCode())) {
          continue;
        }
        if (!name.isEmpty() && !name.contains(species.getName())) {
          continue;
        }
        if (!speciesProperty.isEmpty() && !speciesProperty.contains(species.getSpecies())) {
          continue;
        }
        filteredSpecies.add(species);
      }
    }

    SpeciesResponse speciesResponse = new SpeciesResponse(
        filteredSpecies.toArray(new Species[0]), maxSize, page);

    return Response.ok().entity(speciesResponse).build();
  }

  @GET
  @Path("/{code}")
  public Response getSpeciesByCode(@PathParam("code") String code) {
    Species speciesByCode = speciesDb.get(code);
    if (speciesByCode == null) {
      return Response.status(404).build();
    }
    return Response.ok().entity(speciesByCode).build();
  }
}

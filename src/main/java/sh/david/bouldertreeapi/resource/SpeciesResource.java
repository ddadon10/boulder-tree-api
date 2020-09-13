package sh.david.bouldertreeapi.resource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
  @Operation(
      description = "Species refers to a natural group of trees in the same genus made up of similar individuals.",
      tags = {"3 - Search and Filter among Species"},
      responses = {
          @ApiResponse(description = "A successful search", content = @Content(schema = @Schema(implementation = SpeciesResponse.class)))})
  public Response getSpecies(
      @Context UriInfo uriInfo,
      @Parameter(example = "name") @QueryParam("orderBy") String orderBy,
      @DefaultValue("ASC") @QueryParam("order") Main.orderEnum order,
      @DefaultValue("20") @QueryParam("maxSize") int maxSize,
      @DefaultValue("1") @QueryParam("page") int page,
      @Parameter(example = "[\"ABCO\"]") @QueryParam("code") List<String> code,
      @Parameter(example = "[\"concolor\", \"altissima\"]") @QueryParam("species") List<String> speciesProperty,
      @Parameter(example = "[\"Incense Cedar\"]") @QueryParam("name") List<String> name
  ) {

    List<Species> speciesList = new ArrayList<>(speciesDb.values());
    List<Species> payload = new ArrayList<>();

    if (Main.SPECIAL_QUERYPARAMS.containsAll(uriInfo.getQueryParameters().keySet())) {
      payload = speciesList;
    } else {
      for (Species species : speciesList) {
        boolean found = false;
        if (code.contains(species.getCode())) {
          found = true;
        }
        if (name.contains(species.getName())) {
          found = true;
        }
        if (speciesProperty.contains(species.getSpecies())) {
          found = true;
        }
        if (found) {
          payload.add(species);
        }
      }
    }
    if (orderBy != null) {
      payload = SpeciesResponse.orderPayload(payload, orderBy, order);
    }
    SpeciesResponse speciesResponse = new SpeciesResponse(payload, maxSize, page);
    return Response.ok().entity(speciesResponse).build();
  }

  @GET
  @Path("/{code}")
  @Operation(tags = {"4 - Indexed Resources"},
      responses = {
          @ApiResponse(description = "A successful query", content = @Content(schema = @Schema(implementation = Species.class)))})
  public Response getSpeciesByCode(@Parameter(example = "ABCO") @PathParam("code") String code) {
    Species speciesByCode = speciesDb.get(code);
    if (speciesByCode == null) {
      return Response.status(404).build();
    }
    return Response.ok().entity(speciesByCode).build();
  }
}

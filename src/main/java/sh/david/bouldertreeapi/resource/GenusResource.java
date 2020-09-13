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
import sh.david.bouldertreeapi.datastore.Genus;
import sh.david.bouldertreeapi.response.GenusResponse;

@Path("/genus")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GenusResource {

  TreeMap<String, Genus> genusDb = Main.DATASTORE.getGenusDatabase();

  @GET
  @Path("/")
  @Operation(
      summary = "Genus refers to a group of tree species that have fundamental traits in common but that differ in other, lesser characteristics.",
      tags = {"2 - Search and Filter among Genus"},
      responses = {
          @ApiResponse(description = "A successful search", content = @Content(schema = @Schema(implementation = GenusResponse.class)))})
  public Response getGenus(
      @Context UriInfo uriInfo,
      @Parameter(example = "genusEnglish") @QueryParam("orderBy") String orderBy,
      @DefaultValue("ASC") @QueryParam("order") Main.orderEnum order,
      @DefaultValue("20") @QueryParam("maxSize") int maxSize,
      @DefaultValue("1") @QueryParam("page") int page,
      @Parameter(example = "[\"Magnolia\", \"Prunus\"]") @QueryParam("genus") List<String> genusProperty,
      @Parameter(example = "[\"Tree-of-Heaven\"]") @QueryParam("genusEnglish") List<String> genusEnglish) {
    List<Genus> genusList = new ArrayList<>(genusDb.values());
    List<Genus> payload = new ArrayList<>();
    if (Main.SPECIAL_QUERYPARAMS.containsAll(uriInfo.getQueryParameters().keySet())) {
      payload = genusList;
    } else {
      for (Genus genus : genusList) {
        boolean found = false;
        if (genusProperty.contains(genus.getGenus())) {
          found = true;
        }
        if (genusEnglish.contains(genus.getGenusEnglish())) {
          found = true;
        }
        if (found) {
          payload.add(genus);
        }
      }
    }

    if (orderBy != null) {
      payload = GenusResponse.orderPayload(payload, orderBy, order);
    }
    GenusResponse genusResponse = new GenusResponse(payload, maxSize, page);

    return Response.ok().entity(genusResponse).build();
  }

  @GET
  @Path("/{name}")
  @Operation(tags = {"4 - Indexed Resources"}, responses = {
      @ApiResponse(description = "A successful query", content = @Content(schema = @Schema(implementation = Genus.class)))})
  public Response getGenus(@Parameter(example = "Cercidiphyllum") @PathParam("name") String name) {
    Genus genusByName = genusDb.get(name);
    if (genusByName == null) {
      return Response.status(404).build();
    }
    return Response.ok().entity(genusByName).build();
  }

}

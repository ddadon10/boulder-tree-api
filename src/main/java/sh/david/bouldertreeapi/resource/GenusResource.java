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
import sh.david.bouldertreeapi.datastore.Genus;
import sh.david.bouldertreeapi.response.GenusResponse;

@Path("/genus")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GenusResource {

  TreeMap<String, Genus> genusDb = Main.DATASTORE.getGenusDatabase();

  @GET
  @Path("/")
  public Response getGenus(
      @Context UriInfo uriInfo,
      @QueryParam("orderBy") String orderBy,
      @DefaultValue("ASC") @QueryParam("order") Main.orderEnum order,
      @DefaultValue("-1") @QueryParam("maxSize") int maxSize,
      @DefaultValue("1") @QueryParam("page") int page,
      @QueryParam("genus") List<String> genusProperty,
      @QueryParam("genusEnglish") List<String> genusEnglish) {
    List<Genus> genusList = new ArrayList<>(genusDb.values());
    List<Genus> payload = new ArrayList<>();
    if (Main.SPECIAL_QUERYPARAMS.containsAll(uriInfo.getQueryParameters().keySet())) {
      payload = genusList;
    } else {
      for (Genus genus : genusList) {
        if (!genusProperty.isEmpty() && !genusProperty.contains(genus.getGenus())) {
          continue;
        }
        if (!genusEnglish.isEmpty() && !genusEnglish.contains(genus.getGenusEnglish())) {
          continue;
        }
        payload.add(genus);
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
  public Response getGenus(@PathParam("name") String name) {
    Genus genusByName = genusDb.get(name);
    if (genusByName == null) {
      return Response.status(404).build();
    }
    return Response.ok().entity(genusByName).build();
  }

}

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
import sh.david.bouldertreeapi.datastore.Genus;

import sh.david.bouldertreeapi.utils.PaginatedEntity;

@Path("/genus")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GenusResource {

  TreeMap<String, Genus> genus = Main.DATASTORE.getGenusDatabase();

  @GET
  @Path("/")
  public Response getGenus(
      @DefaultValue("-1") @QueryParam("maxSize") int maxSize,
      @DefaultValue("1") @QueryParam("page") int page) {
    PaginatedEntity<Genus> paginatedEntity;
    if (maxSize > 0 && page > 0) {
      paginatedEntity = new PaginatedEntity<>(genus.values().toArray(new Genus[0]), page, maxSize);
    } else {
      paginatedEntity = new PaginatedEntity<>(genus.values().toArray(new Genus[0]));
    }

    return Response.ok().entity(paginatedEntity).build();
  }

  @GET
  @Path("/{name}")
  public Response getGenus(@PathParam("name") String name) {
    Genus genusByName = genus.get(name);
    if (genusByName == null) {
      return Response.status(404).build();
    }
    return Response.ok().entity(genusByName).build();
  }

}

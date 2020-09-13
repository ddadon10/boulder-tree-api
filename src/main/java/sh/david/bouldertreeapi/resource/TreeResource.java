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
import sh.david.bouldertreeapi.datastore.Dimensions;
import sh.david.bouldertreeapi.datastore.Genus;
import sh.david.bouldertreeapi.response.TreeResponse;
import sh.david.bouldertreeapi.datastore.Form;
import sh.david.bouldertreeapi.datastore.LeafCycle;
import sh.david.bouldertreeapi.datastore.LeafFallColor;
import sh.david.bouldertreeapi.datastore.LeafType;
import sh.david.bouldertreeapi.datastore.Species;
import sh.david.bouldertreeapi.datastore.Tree;
import sh.david.bouldertreeapi.datastore.WaterNeed;

@Path("/trees")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TreeResource {

  TreeMap<Integer, Tree> treesDb = Main.DATASTORE.getTreeDatabase();

  @GET
  @Path("/")
  @Operation(
      description = "A Tree represent an actual Tree in Boulder, CO. Each tree has many properties like name, species or genus.",
      tags = {"1 - Search and Filter among Trees"},
      responses = {
          @ApiResponse(description = "A successful search", content = @Content(schema = @Schema(implementation = TreeResponse.class)))})
  public Response getTrees(
      @Context() UriInfo uriInfo,
      @Parameter(example = "dimensions.minHeight") @QueryParam("orderBy") String orderBy,
      @DefaultValue("ASC") @QueryParam("order") Main.orderEnum order,
      @DefaultValue("20") @QueryParam("maxSize") int maxSize,
      @DefaultValue("1") @QueryParam("page") int page,
      @Parameter(example = "[1,201,236]") @QueryParam("id") List<Integer> id,
      @Parameter(example = "[\"White fir\", \"Red maple\"]") @QueryParam("commonName") List<String> commonName,
      @Parameter(example = "[\"Acer saccharum\"]") @QueryParam("latinName") List<String> latinName,
      @Parameter(description = "I know it's edgy but you can send a JSON or XML Representation of a Species :p<br/>You can do Fuzzy search also! To get all the Maple Trees: `{\"name\":\"Maple\"}`") @QueryParam("species") List<Species> species,
      @Parameter(description = "I know it's edgy but you can send a JSON or XML Representation of a Genus :p  <br/> Eg: `{\"genus\":\"Aesculus\"}`") @QueryParam("genus") List<Genus> genus,
      @QueryParam("leafCylce") List<LeafCycle> leafCycle,
      @QueryParam("leafType") List<LeafType> leafType,
      @QueryParam("leafFallColorList") List<LeafFallColor> leafFallColorList,
      @Parameter(description = "I know it's edgy but you can send a JSON or XML Representation of a Dimension :p <br/> Eg: `{\"minHeight\":\"20\"}`") @QueryParam("dimensions") List<Dimensions> dimensions,
      @QueryParam("formList") List<Form> formList,
      @Parameter(example = "[\"yellow clusters\", \"white, orchid-like\"]") @QueryParam("flower") List<String> flower,
      @Parameter(example = "[\"berry\"]") @QueryParam("fruit") List<String> fruit,
      @QueryParam("waterNeed") List<WaterNeed> waterNeed
  ) {
    List<Tree> treeList = new ArrayList<>(treesDb.values());
    List<Tree> payload = new ArrayList<>();
    if (Main.SPECIAL_QUERYPARAMS.containsAll(uriInfo.getQueryParameters().keySet())) {
      payload = treeList;
    } else {
      for (Tree tree : treeList) {
        boolean found = false;
        if (id.contains(tree.getId())) {
          found = true;
        } else if (commonName.contains(tree.getCommonName())) {
          found = true;
        } else if (latinName.contains(tree.getLatinName())) {
          found = true;
        } else if (species.stream().anyMatch(tree.getSpecies()::goodEnoughEquals)) {
          found = true;
        } else if (genus.stream().anyMatch(tree.getGenus()::goodEnoughEquals)) {
          found = true;
        } else if (leafCycle.contains(tree.getLeafCycle())) {
          found = true;
        } else if (leafType.contains(tree.getLeafType())) {
          found = true;
        } else if (leafFallColorList.stream()
            .anyMatch(el -> tree.getLeafFallColorList().contains(el))) {
          found = true;
        } else if (dimensions.stream()
            .anyMatch(tree.getDimensions()::goodEnoughEquals)) {
          found = true;
        } else if (formList.stream()
            .anyMatch(el -> tree.getFormList().contains(el))) {
          found = true;
        } else if (flower.contains(tree.getFlower())) {
          found = true;
        } else if (fruit.contains(tree.getFruit())) {
          found = true;
        } else if (waterNeed.contains(tree.getWaterNeed())) {
          found = true;
        }
        if (found) {
          payload.add(tree);
        }
      }

    }

    if (orderBy != null) {
      payload = TreeResponse.orderPayload(payload, orderBy, order);
    }
    TreeResponse treeResponse = new TreeResponse(payload, maxSize, page);
    return Response.ok().entity(treeResponse).build();
  }

  @GET
  @Path("/{id}")
  @Operation(tags = {"Other - Get Resource by Id"})
  public Response getTreeById(@PathParam("id") int id) {
    Tree tree = treesDb.get(id);
    if (tree == null) {
      return Response.status(404).build();
    }
    return Response.ok().entity(tree).build();

  }


}

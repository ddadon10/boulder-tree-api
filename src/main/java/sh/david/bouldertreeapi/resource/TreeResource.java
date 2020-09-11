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
import sh.david.bouldertreeapi.datastore.Dimensions;
import sh.david.bouldertreeapi.datastore.Genus;
import sh.david.bouldertreeapi.utils.ResponseEntity;
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

  TreeMap<Integer, Tree> trees = Main.DATASTORE.getTreeDatabase();

  @GET
  @Path("/")
  public Response getTrees(
      @Context() UriInfo uriInfo,
      @QueryParam("orderBy") String orderBy,
      @DefaultValue("-1") @QueryParam("maxSize") int maxSize,
      @DefaultValue("1") @QueryParam("page") int page,
      @QueryParam("id") List<Integer> id,
      @QueryParam("commonName") List<String> commonName,
      @QueryParam("latinName") List<String> latinName,
      @QueryParam("species") List<Species> species,
      @QueryParam("genus") List<Genus> genus,
      @QueryParam("leafCylce") List<LeafCycle> leafCycle,
      @QueryParam("leafType") List<LeafType> leafType,
      @QueryParam("leafFallColorList") List<LeafFallColor> leafFallColorList,
      @QueryParam("dimensions") List<Dimensions> dimensions,
      @QueryParam("formList") List<Form> formList,
      @QueryParam("flower") List<String> flower,
      @QueryParam("fruit") List<String> fruit,
      @QueryParam("waterNeed") List<WaterNeed> waterNeed
  ) {
    List<Tree> treeList = new ArrayList<>(trees.values());
    List<Tree> filteredTrees = new ArrayList<>();
    ResponseEntity<Tree> responseEntity = new ResponseEntity<>();

    if (Main.SPECIAL_QUERYPARAMS.containsAll(uriInfo.getQueryParameters().keySet())) {
      filteredTrees = treeList;
    } else {
      for (Tree tree : treeList) {
        if (!id.isEmpty() && !id.contains(tree.getId())) {
          continue;
        }
        if (!commonName.isEmpty() && !commonName.contains(tree.getCommonName())) {
          continue;
        }
        if (!latinName.isEmpty() && !latinName.contains(tree.getLatinName())) {
          continue;
        }
        if (!species.isEmpty() && species.stream().noneMatch(tree.getSpecies()::goodEnoughEquals)) {
          continue;
        }
        if (!genus.isEmpty() && genus.stream().noneMatch(tree.getGenus()::goodEnoughEquals)) {
          continue;
        }
        if (!leafCycle.isEmpty() && !leafCycle.contains(tree.getLeafCycle())) {
          continue;
        }
        if (!leafType.isEmpty() && !leafType.contains(tree.getLeafType())) {
          continue;
        }
        if (!leafFallColorList.isEmpty() && leafFallColorList.stream()
            .noneMatch(el -> tree.getLeafFallColorList().contains(el))) {
          continue;
        }
        if (!dimensions.isEmpty() && dimensions.stream()
            .noneMatch(tree.getDimensions()::goodEnoughEquals)) {
          continue;
        }
        if (!formList.isEmpty() && formList.stream()
            .noneMatch(el -> tree.getFormList().contains(el))) {
          continue;
        }
        if (!flower.isEmpty() && !flower.contains(tree.getFlower())) {
          continue;
        }
        if (!fruit.isEmpty() && !fruit.contains(tree.getFruit())) {
          continue;
        }
        if (!waterNeed.isEmpty() && !waterNeed.contains(tree.getWaterNeed())) {
          continue;
        }
        filteredTrees.add(tree);
      }

    }

    if (maxSize > 0 && page > 0) {
      int filteredTreesSize = filteredTrees.size();
      int pageIndex = Math.min((page - 1) * maxSize, filteredTreesSize);
      int maxSizeIndex = Math.min(pageIndex + maxSize, filteredTreesSize);
      double numberOfPage = Math.ceil((float) filteredTreesSize / maxSize);
      responseEntity.setCurrentPage(page);
      responseEntity.setNumberOfPage(numberOfPage);
      filteredTrees = filteredTrees.subList(pageIndex, maxSizeIndex);

    }
    responseEntity.setPayload(filteredTrees.toArray(new Tree[0]));

    System.out.print(1);
    return Response.ok().entity(responseEntity).build();
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

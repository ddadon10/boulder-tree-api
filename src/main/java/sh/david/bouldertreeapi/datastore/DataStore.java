package sh.david.bouldertreeapi.datastore;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class DataStore {

  private final Set<Tree> treeSet = new HashSet<>();
  private final Set<Genus> genusSet = new HashSet<>();
  private final Set<Species> speciesSet = new HashSet<>();

  public DataStore() throws FileNotFoundException, URISyntaxException {
    List<HashMap<String, String>> rawData = this.loadRawData();
    for (HashMap<String, String> data : rawData) {
      Genus genus = this.addToGenusSet(data);
      Species species = this.addToSpeciesSet(data);
      Tree tree = this.addToTreeSet(data, species, genus);
    }
  }

  private Tree addToTreeSet(HashMap<String, String> data, Species species, Genus genus) {
    final String treeIdKey = "TREEID";
    final String commonNameKey = "COMMONNAME";
    final String latinNameKey = "LATINNAME";
    final String leafCycleKey = "LEAFCYCLE";
    final String leafTypeKey = "LEAFTYPE";
    final String mathHeightKey = "MATHEIGHT";
    final String mathWidthKey = "MATWIDTH";
    final String formKey = "FORM";
    final String leafFallColorKey = "FALLCOLOR";
    final String flowerKey = "FLOWER";
    final String fruitKey = "FRUIT";
    final String waterNeedKey = "WATERNEED";
    final Dimensions dimensions = new Dimensions(data.get(mathHeightKey), data.get(mathWidthKey));
    final String waterNeed = data.get(waterNeedKey).toUpperCase().trim()
        .replace(' ', '_');
    final String leafCycle = data.get(leafCycleKey).toUpperCase().trim();
    final String leafType = data.get(leafTypeKey).toUpperCase().trim();

    List<LeafFallColor> leafFallColorList = Arrays
        .stream(data.get(leafFallColorKey).split(","))
        .filter(s -> !s.isEmpty())
        .map(String::toUpperCase)
        .map(String::trim)
        .map(LeafFallColor::valueOf)
        .collect(Collectors.toList());

    List<Form> formList = Arrays
        .stream(data.get(formKey).split(","))
        .filter(s -> !s.isEmpty())
        .map(String::toUpperCase)
        .map(String::trim)
        .map(Form::valueOf)
        .collect(Collectors.toList());

    Tree tree = new Tree(Integer.parseInt(data.get(treeIdKey)), data.get(commonNameKey),
        data.get(latinNameKey), species, genus,
        LeafCycle.valueOf(leafCycle.isEmpty() ? LeafCycle.NOT_SET.toString() : leafCycle),
        LeafType.valueOf(leafType.isEmpty() ? LeafType.NOT_SET.toString() : leafType),
        leafFallColorList, dimensions,
        formList,
        data.get(flowerKey), data.get(fruitKey),
        WaterNeed.valueOf(waterNeed.isEmpty() ? WaterNeed.NOT_SET.toString(): waterNeed));

    treeSet.add(tree);
    return tree;
  }


  private Genus addToGenusSet(HashMap<String, String> data) {
    final String genusKey = "GENUS";
    final String genusEnglishKey = "GENUSENGLISH";
    Genus genus = new Genus(data.get(genusKey), data.get(genusEnglishKey));
    genusSet.add(genus);
    return genus;
  }

  private Species addToSpeciesSet(HashMap<String, String> data) {
    final String speciesCodeKey = "SPECIESCODE";
    final String speciesKey = "SPECIES";
    final String speciesCodeName = "SPECIESNAME";
    Species species = new Species(data.get(speciesCodeKey), data.get(speciesKey),
        data.get(speciesCodeName));
    speciesSet.add(species);
    return species;
  }

  public Set<Tree> getTreeSet() {
    return this.treeSet;
  }

  public Set<Genus> getGenusSet() {
    return this.genusSet;
  }

  public Set<Species> getSpeciesSet() {
    return this.speciesSet;
  }

  private List<HashMap<String, String>> loadRawData()
      throws URISyntaxException, FileNotFoundException {
    List<HashMap<String, String>> data = new ArrayList<>();
    List<String> header = new ArrayList<>();
    URL dbUrl = getClass().getResource("/Tree_Species_in_Boulder.csv");
    Scanner scanner = new Scanner(new File(dbUrl.toURI()));

    while (scanner.hasNextLine()) {
      String separator = ";";
      List<String> row = Arrays.asList(scanner.nextLine().split(separator, -1));
      if (header.isEmpty()) {
        header.addAll(row);
        continue;
      }

      HashMap<String, String> element = (HashMap<String, String>) IntStream
          .range(0, header.size())
          .boxed()
          .collect(Collectors.toMap(header::get, row::get));

      data.add(element);
    }
    return data;
  }

}

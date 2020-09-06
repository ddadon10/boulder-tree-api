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

  private Set<Tree> treeSet = new HashSet<>();
  private Set<Genus> genusSet = new HashSet<>();
  private Set<Species> speciesSet = new HashSet<>();

  public DataStore() throws FileNotFoundException, URISyntaxException {
    List<HashMap<String, String>> rawData = this.loadRawData();
    for (HashMap<String, String> data : rawData) {
      Genus genus = this.addToGenusSet(data);
      Species species = this.addToSpeciesSet(data);
    }
      System.out.print(1);
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

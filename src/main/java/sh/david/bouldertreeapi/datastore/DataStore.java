package sh.david.bouldertreeapi.datastore;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.Scanner;

public class DataStore {
  private List<Tree> treeList;
  private List<Leaf> leafList;
  private List<Genus> genusList;
  private List<Species> speciesList;

  public DataStore() throws FileNotFoundException, URISyntaxException {
    URL dbUrl = getClass().getResource("/Tree_Species_in_Boulder.csv");
    Scanner scanner = new Scanner(new File(dbUrl.toURI()));
    while (scanner.hasNextLine()) {
      // Todo: Implement data retrieval;
    }
    
  }

  public List<Tree> getTreeList() {
    return this.treeList;
  }

  public List<Leaf> getLeafList() {
    return this.leafList;
  }

  public List<Genus> getGenusList() {
    return this.genusList;
  }

  public List<Species> getSpeciesList() {
    return this.speciesList;
  }

}

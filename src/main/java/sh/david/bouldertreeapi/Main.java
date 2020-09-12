package sh.david.bouldertreeapi;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import sh.david.bouldertreeapi.datastore.DataStore;

/**
 * Main class.
 */
public class Main {
  public static DataStore DATASTORE;
  public static ArrayList<String> SPECIAL_QUERYPARAMS = new ArrayList<>();
  public static enum orderEnum {ASC, DESC}

  static {
    try {
      SPECIAL_QUERYPARAMS.add("maxSize");
      SPECIAL_QUERYPARAMS.add("page");
      SPECIAL_QUERYPARAMS.add("orderBy");
      SPECIAL_QUERYPARAMS.add("order");
      DATASTORE = new DataStore();
    } catch (FileNotFoundException | URISyntaxException e) {
      e.printStackTrace();
    }
  }
}

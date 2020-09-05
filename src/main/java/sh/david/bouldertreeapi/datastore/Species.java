package sh.david.bouldertreeapi.datastore;

public class Species {

  final private String code;
  final private String species;
  final private String name;

  Species(String code, String species, String name) {
    this.code = code;
    this.species = species;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public String getSpecies() {
    return this.species;
  }

  public String getName() {
    return this.name;
  }
}

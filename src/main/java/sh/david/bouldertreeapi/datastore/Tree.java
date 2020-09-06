package sh.david.bouldertreeapi.datastore;

import java.util.List;

public class Tree {

  private int id;
  private String commonName;
  private String latinName;
  private Species species;
  private Genus genus;
  private Leaf leaf;
  private Dimensions dimensions;
  private List<Form> formList;
  private String flower;
  private String fruit;
  private WaterNeed waterNeed;

  Tree(int id, String commonName, String latinName, Species species, Genus genus, Leaf leaf,
      Dimensions dimensions, List<Form> formList, String flower, String fruit,
      WaterNeed waterNeed) {
    this.id = id;
    this.commonName = commonName;
    this.latinName = latinName;
    this.species = species;
    this.genus = genus;
    this.leaf = leaf;
    this.dimensions = dimensions;
    this.formList = formList;
    this.flower = flower;
    this.fruit = fruit;
    this.waterNeed = waterNeed;
  }

  public int getId() {
    return this.id;
  }

  public String getCommonName() {
    return this.commonName;
  }

  public String getLatinName() {
    return this.latinName;
  }

  public Species getSpecies() {
    return this.species;
  }

  public Genus getGenus() {
    return this.genus;
  }

  public Leaf getLeaf() {
    return this.leaf;
  }

  public Dimensions getDimensions() {
    return this.dimensions;
  }

  public List<Form> getFormList() {
    return this.formList;
  }

  public String getFlower() {
    return this.flower;
  }

  public String getFruit() {
    return this.fruit;
  }

  public WaterNeed getWaterNeed() {
    return this.waterNeed;
  }
}

class Dimensions {

  private final int maxHeight;
  private final int minWidth;
  private final int minHeight;
  private final int maxWidth;

  public int getMinHeight() {
    return this.minHeight;
  }

  public int getMaxHeight() {
    return this.maxHeight;
  }

  public int getMinWidth() {
    return this.minWidth;
  }

  public int getMaxWidth() {
    return this.maxWidth;
  }

  Dimensions(int minHeight, int maxHeight, int minWidth, int maxWidth) {
    this.minHeight = minHeight;
    this.maxHeight = maxHeight;
    this.minWidth = minWidth;
    this.maxWidth = maxWidth;
  }
}

enum Form {
  OVAL, ROUNDED, PYRIMIDAL, IRREGULAR, VASE, UPRIGHT;
}

enum WaterNeed {
  LOW, LOW_TO_MEDIUM, MEDIUM, MEDIUM_TO_HIGH, HIGH;
}
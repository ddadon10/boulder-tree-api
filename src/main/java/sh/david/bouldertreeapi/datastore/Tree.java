package sh.david.bouldertreeapi.datastore;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tree {
  @XmlElement
  private int id;
  @XmlElement
  private String commonName;
  @XmlElement
  private String latinName;
  @XmlElement
  private Species species;
  @XmlElement
  private Genus genus;
  @XmlElement
  private LeafCycle leafCycle;
  @XmlElement
  private LeafType leafType;
  @XmlElement
  private List<LeafFallColor> leafFallColorList;
  @XmlElement
  private Dimensions dimensions;
  @XmlElement
  private List<Form> formList;
  @XmlElement
  private String flower;
  @XmlElement
  private String fruit;
  @XmlElement
  private WaterNeed waterNeed;

  public Tree(){}

  public Tree(int id, String commonName, String latinName, Species species, Genus genus,
      LeafCycle leafCycle, LeafType leafType, List<LeafFallColor> leafFallColorList,
      Dimensions dimensions, List<Form> formList, String flower, String fruit,
      WaterNeed waterNeed) {
    this.id = id;
    this.commonName = commonName;
    this.latinName = latinName;
    this.species = species;
    this.genus = genus;
    this.leafCycle = leafCycle;
    this.leafType = leafType;
    this.leafFallColorList = leafFallColorList;
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

  public LeafCycle getLeafCycle() {
    return this.leafCycle;
  }

  public LeafType getLeafType() {
    return this.leafType;
  }

  public List<LeafFallColor> getLeafFallColorList() {
    return this.leafFallColorList;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tree OtherTree = (Tree) o;
    return this.getId() == OtherTree.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }
}


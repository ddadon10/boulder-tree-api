package sh.david.bouldertreeapi.datastore;

import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Species {
  @XmlElement
  private String code;
  @XmlElement
  private String species;
  @XmlElement
  private String name;

  public Species(){}
  public Species(String code, String species, String name) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Species otherSpecies = (Species) o;
    return this.code.equals(otherSpecies.getCode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }
}

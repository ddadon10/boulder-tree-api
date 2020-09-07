package sh.david.bouldertreeapi.datastore;

import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Genus {

  @XmlElement
  private String genus;
  @XmlElement
  private String genusEnglish;

  public Genus(){}
  public Genus(String genus, String genusEnglish) {
    this.genus = genus;
    this.genusEnglish = genusEnglish;
  }

  String getGenus() {
    return this.genus;
  }

  String getGenusEnglish() {
    return this.genusEnglish;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Genus otherGenus = (Genus) o;
    return this.genus.equals(otherGenus.getGenus());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.genus);
  }

}

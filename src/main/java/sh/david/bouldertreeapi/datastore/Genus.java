package sh.david.bouldertreeapi.datastore;

import java.util.Objects;

public class Genus {

  final private String genus;
  final private String genusEnglish;

  Genus(String genus, String genusEnglish) {
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

package sh.david.bouldertreeapi.datastore;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

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
  public int hashCode() {
    return 1;
  }

}

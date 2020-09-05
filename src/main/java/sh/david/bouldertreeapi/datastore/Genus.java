package sh.david.bouldertreeapi.datastore;

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

}

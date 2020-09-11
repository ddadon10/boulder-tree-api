package sh.david.bouldertreeapi.datastore;

import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONException;
import sh.david.bouldertreeapi.datastore.interfaces.FuzzySearch;
import sh.david.bouldertreeapi.utils.Utils;

@XmlRootElement
public class Genus implements FuzzySearch<Genus> {

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

  public static Genus fromString(String string){
    try{
      return Utils.unmarshal(string, Genus.class);
    } catch (JSONException | IllegalArgumentException e) {
      e.printStackTrace();
      // Todo: Return 500 instead
      return new Genus();
    }
  }

  @Override
  public boolean goodEnoughEquals(Genus otherGenus) {
    if (otherGenus.getGenus() != null && this.getGenus().equals(otherGenus.getGenus())){
      return true;
    }

    if(otherGenus.getGenusEnglish() != null && this.getGenusEnglish().equals(otherGenus.getGenusEnglish())){
      return true;
    }
    return false;
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

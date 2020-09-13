package sh.david.bouldertreeapi.datastore;

import java.util.Objects;
import javax.ws.rs.BadRequestException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.JSONException;
import sh.david.bouldertreeapi.datastore.interfaces.FuzzySearch;
import sh.david.bouldertreeapi.utils.Utils;

@XmlRootElement
public class Dimensions implements FuzzySearch<Dimensions> {

  @XmlElement
  private int minHeight = -1;
  @XmlElement
  private int maxHeight = -1;
  @XmlElement
  private int minWidth = -1;
  @XmlElement
  private int maxWidth = -1;

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

  public Dimensions() {
  }

  public Dimensions(String height, String width) {
    if (!height.isEmpty()) {
      String[] heightParsed = height.split("-");
      this.minHeight = Integer.parseInt(heightParsed[0]);
      this.maxHeight = Integer.parseInt(heightParsed[1]);
    }
    if (!width.isEmpty()) {
      String[] widthParsed = width.split("-");
      this.minWidth = Integer.parseInt(widthParsed[0]);
      this.maxWidth = Integer.parseInt(widthParsed[1]);
    }

  }

  public static Dimensions fromString(String string){
    try{
      return Utils.unmarshal(string, Dimensions.class);
    } catch (JSONException | IllegalArgumentException e) {
      e.printStackTrace();
      throw new BadRequestException("Incorrect representation for " + Dimensions.class.getName());
    }
  }

  @Override
  public boolean goodEnoughEquals(Dimensions otherDimensions) {

    if (otherDimensions.getMinHeight() != -1 && this.getMinHeight() == otherDimensions.getMinHeight()){
      return true;
    }
    if (otherDimensions.getMaxHeight() != -1 && this.getMaxHeight() == otherDimensions.getMaxHeight()){
      return true;
    }
    if (otherDimensions.getMinWidth() != -1 && this.getMinWidth() == otherDimensions.getMinWidth()){
      return true;
    }
    if (otherDimensions.getMaxWidth() != -1 && this.getMaxWidth() == otherDimensions.getMaxWidth()){
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
    Dimensions otherDimensions = (Dimensions) o;
    return this.getMaxHeight() == otherDimensions.getMaxHeight() &&
        this.getMinWidth() == otherDimensions.getMinWidth() &&
        this.getMinHeight() == otherDimensions.getMinHeight() &&
        this.getMaxWidth() == otherDimensions.getMaxWidth();
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(this.getMaxHeight(), this.getMinWidth(), this.getMinHeight(), this.getMaxWidth());
  }
}

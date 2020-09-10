package sh.david.bouldertreeapi.datastore;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Dimensions {

  @XmlElement
  private int maxHeight = -1;
  @XmlElement
  private int minWidth = -1;
  @XmlElement
  private int minHeight = -1;
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

  Dimensions() {
  }

  Dimensions(String height, String width) {
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
}

package sh.david.bouldertreeapi.utils;

import java.util.Arrays;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import sh.david.bouldertreeapi.datastore.Genus;
import sh.david.bouldertreeapi.datastore.Species;
import sh.david.bouldertreeapi.datastore.Tree;

@XmlRootElement
@XmlSeeAlso({Tree.class, Genus.class, Species.class})
public class PaginatedEntity<T> {

  @XmlElement
  private T[] payload;
  @XmlElement
  private int page = 1;
  @XmlElement
  private double numberOfPage = 1;
  @XmlElement
  private int payloadLength = 1;


  public PaginatedEntity() {
  }

  public PaginatedEntity(T[] payload, int maxSize, int page) {
    if (maxSize <= 0 || page <= 0) {
      this.payload = payload;
    } else {
      int payloadSize = payload.length;
      int pageIndex = Math.min((page - 1) * maxSize, payloadSize);
      int maxSizeIndex = Math.min(pageIndex + maxSize, payloadSize);

      this.page = page;
      this.numberOfPage = Math.ceil((float) payloadSize / maxSize);
      this.payload = Arrays.copyOfRange(payload, pageIndex, maxSizeIndex);
    }
    this.payloadLength = this.payload.length;
  }

}

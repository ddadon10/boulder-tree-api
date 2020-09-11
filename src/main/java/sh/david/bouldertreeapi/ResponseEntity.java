package sh.david.bouldertreeapi;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import sh.david.bouldertreeapi.datastore.Tree;

@XmlRootElement
@XmlSeeAlso({Tree.class})
public class ResponseEntity<T> {

  @XmlElement
  private T[] payload;
  @XmlElement
  private int currentPage = 1;
  @XmlElement
  private double numberOfPage = 1;

  public ResponseEntity() {
  }

  public void setPayload(T[] payload) {
    this.payload = payload;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public void setNumberOfPage(double numberOfPage) {
    this.numberOfPage = numberOfPage;
  }
}

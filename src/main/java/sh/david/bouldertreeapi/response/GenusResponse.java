package sh.david.bouldertreeapi.response;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import sh.david.bouldertreeapi.datastore.Genus;

@XmlRootElement
public class GenusResponse extends BaseResponse<Genus> {

  @Override
  @XmlElementWrapper(name="genusList")
  @XmlElement(name="genus")
  // I have to do that otherwise JAXB doesn't find the class for unmarshalling
  // because of generic type erasure  and @XmlSeeAlso polluted my payload
  public Genus[] getPaginatedEntity() {
    return this.paginatedEntity;
  }

  public GenusResponse(){
    super();
  };

  public GenusResponse(List<Genus> payload, int maxSize, int page){
    super(payload.toArray(new Genus[0]), maxSize, page);
  }


}

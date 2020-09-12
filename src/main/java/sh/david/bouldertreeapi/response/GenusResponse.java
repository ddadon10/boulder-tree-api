package sh.david.bouldertreeapi.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import sh.david.bouldertreeapi.datastore.Genus;

@XmlRootElement
public class GenusResponse extends BaseResponse<Genus> {

  @Override
  @XmlElementWrapper(name="GenusList")
  @XmlElement(name="Genus")
  public Genus[] getPaginatedEntity() {
    return this.paginatedEntity;
  }

  public GenusResponse(){
    super();
  };

  public GenusResponse(Genus[] payload, int maxSize, int page){
    super(payload, maxSize, page);
  }


}

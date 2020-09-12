package sh.david.bouldertreeapi.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import sh.david.bouldertreeapi.datastore.Species;

@XmlRootElement
public class SpeciesResponse extends BaseResponse<Species> {

  @Override
  @XmlElementWrapper(name="SpeciesList")
  @XmlElement(name="Species")
  public Species[] getPaginatedEntity() {
    return this.paginatedEntity;
  }

  public SpeciesResponse(){
    super();
  };

  public SpeciesResponse(Species[] payload, int maxSize, int page){
    super(payload, maxSize, page);
  }


}

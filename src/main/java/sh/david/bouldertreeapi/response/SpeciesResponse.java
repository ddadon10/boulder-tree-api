package sh.david.bouldertreeapi.response;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import sh.david.bouldertreeapi.datastore.Species;

@XmlRootElement
public class SpeciesResponse extends BaseResponse<Species> {

  @Override
  @XmlElementWrapper(name="speciesList")
  @XmlElement(name="species")
  public Species[] getPaginatedEntity() {
    return this.paginatedEntity;
  }

  public SpeciesResponse(){
    super();
  };

  public SpeciesResponse(List<Species> payload, int maxSize, int page){
    super(payload.toArray(new Species[0]), maxSize, page);
  }


}

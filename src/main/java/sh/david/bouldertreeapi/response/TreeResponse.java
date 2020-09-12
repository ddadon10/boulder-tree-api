package sh.david.bouldertreeapi.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import sh.david.bouldertreeapi.datastore.Tree;

@XmlRootElement
public class TreeResponse extends BaseResponse<Tree> {

  @Override
  @XmlElementWrapper(name="Trees")
  @XmlElement(name="Tree")
  public Tree[] getPaginatedEntity() {
    return this.paginatedEntity;
  }

  public TreeResponse(){
    super();
  };

  public TreeResponse(Tree[] payload, int maxSize, int page){
    super(payload, maxSize, page);
  }


}

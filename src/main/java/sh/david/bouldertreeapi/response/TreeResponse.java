package sh.david.bouldertreeapi.response;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import sh.david.bouldertreeapi.datastore.Tree;

@XmlRootElement
public class TreeResponse extends BaseResponse<Tree> {

  @Override
  @XmlElementWrapper(name="trees")
  @XmlElement(name="tree")
  public Tree[] getPaginatedEntity() {
    return this.paginatedEntity;
  }

  public TreeResponse(){
    super();
  };

  public static Tree[] convertAndOrderPayload(List<Tree> payload, String orderBy){
    return orderPayload(payload, orderBy).toArray(new Tree[0]);
  }

  public TreeResponse(Tree[] payload, int maxSize, int page){
    super(payload, maxSize, page);
  }


}

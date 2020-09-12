package sh.david.bouldertreeapi.response;

import java.util.Arrays;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public abstract class BaseResponse<T> {
  @XmlTransient
  protected T[] paginatedEntity;
  @XmlElement
  private int page = 1;
  @XmlElement
  private double numberOfPage = 1;

  public BaseResponse(){}

  public abstract T[] getPaginatedEntity();

  public BaseResponse(T[] payload, int maxSize, int page) {
    if (maxSize <= 0 || page <= 0) {
      this.paginatedEntity = payload;
    } else {
      int payloadSize = payload.length;
      int pageIndex = Math.min((page - 1) * maxSize, payloadSize);
      int maxSizeIndex = Math.min(pageIndex + maxSize, payloadSize);

      this.page = page;
      this.numberOfPage = Math.ceil((float) payloadSize / maxSize);
      this.paginatedEntity = Arrays.copyOfRange(payload, pageIndex, maxSizeIndex);
    }
  }
}

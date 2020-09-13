package sh.david.bouldertreeapi.response;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.BadRequestException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.beanutils.BeanComparator;
import sh.david.bouldertreeapi.Main.orderEnum;

public abstract class BaseResponse<T> {

  @XmlTransient
  protected T[] paginatedEntity;
  @XmlElement
  private int page = 1;
  @XmlElement
  private double numberOfPage = 1;
  @XmlElement
  private int numberOfElementsPerPage;

  public BaseResponse() {
  }

  public abstract T[] getPaginatedEntity();

  public static <T> List<T> orderPayload(List<T> payload, String orderBy, orderEnum order) {
    try{
      BeanComparator<T> beanComparator = new BeanComparator<>(orderBy);
      return payload.stream()
          .sorted(order == orderEnum.ASC ? beanComparator : beanComparator.reversed())
          .collect(Collectors.toList());
    } catch (RuntimeException e){
      e.printStackTrace();
      throw new BadRequestException("Bad Request: orderBy queryParameter not correct!");
    }
  }

  public BaseResponse(T[] payload, int maxSize, int page) {
    if (maxSize < 1 || page < 1) {
      throw new BadRequestException("Bad Request: maxSize or page can't be lower than 1");
    } else {
      int payloadSize = payload.length;
      int pageIndex = Math.min((page - 1) * maxSize, payloadSize);
      int maxSizeIndex = Math.min(pageIndex + maxSize, payloadSize);

      this.page = page;
      this.numberOfPage = Math.ceil((float) payloadSize / maxSize);
      this.paginatedEntity = Arrays.copyOfRange(payload, pageIndex, maxSizeIndex);
    }
    this.numberOfElementsPerPage = this.paginatedEntity.length;
  }

  public int getPage() {
    return this.page;
  }

  public int getNumberOfElementsPerPage() {
    return this.numberOfElementsPerPage;
  }

  public double getNumberOfPage() {
    return numberOfPage;
  }

}

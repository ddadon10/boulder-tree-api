package sh.david.bouldertreeapi.utils;

import java.io.StringReader;
import javax.xml.bind.JAXB;
import org.json.JSONObject;
import org.json.XML;

public class Utils {
  public static <T> T unmarshalJson(String string, Class<T> classToCast){
    String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    String xmlTemplate = "%s<root>%s</root>";
    JSONObject jsonObject = new JSONObject(string);
    String xmlPayload = XML.toString(jsonObject);
    String xml = String.format(xmlTemplate,xmlHeader,xmlPayload);
    T objectUnmarshalled = JAXB.unmarshal(new StringReader(xml), classToCast);
    return objectUnmarshalled;
  }

}

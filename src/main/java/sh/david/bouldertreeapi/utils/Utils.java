package sh.david.bouldertreeapi.utils;

import java.io.StringReader;
import javax.xml.bind.JAXB;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Utils {

  public static <T> T unmarshal(String string, Class<T> classToCast)
      throws JSONException, IllegalArgumentException {
    String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    String xmlTemplate = "%s<root>%s</root>";
    String xml;
    String xmlPayload;
    if (string.charAt(0) == '{') {
      JSONObject jsonObject = new JSONObject(string);
      xmlPayload = XML.toString(jsonObject);
      xml = String.format(xmlTemplate, xmlHeader, xmlPayload);
    } else if (string.charAt(0) == '<') {
      xml = String.format(xmlTemplate, xmlHeader, string);
    } else {
      throw new IllegalArgumentException();
    }

    T objectUnmarshalled = JAXB.unmarshal(new StringReader(xml), classToCast);
    return objectUnmarshalled;
  }

}

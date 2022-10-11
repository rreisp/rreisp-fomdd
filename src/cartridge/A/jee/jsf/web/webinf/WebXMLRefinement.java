package cartridge.A.jee.jsf.web.webinf;

import core.mvc.model.*;
import java.util.Vector;

public class WebXMLRefinement
{
  protected static String nl;
  public static synchronized WebXMLRefinement create(String lineSeparator)
  {
    nl = lineSeparator;
    WebXMLRefinement result = new WebXMLRefinement();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
  protected final String TEXT_2 = NL + "<xr:refine xmlns:xr=\"http://www.atarix.org/xmlRefinement\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.atarix.org/xmlRefinement\">";
  protected final String TEXT_3 = NL + "<xr:at select=\"//web-app\">" + NL + "<xr:prepend>" + NL + "\t<listener>" + NL + "\t\t<listener-class>model.repository.";
  protected final String TEXT_4 = "</listener-class>" + NL + "\t</listener>" + NL + "</xr:prepend>" + NL + "</xr:at>";
  protected final String TEXT_5 = NL + "<xr:at select=\"//web-app\">" + NL + "<xr:prepend>" + NL + "\t<listener>" + NL + "\t\t<listener-class>model.repository.";
  protected final String TEXT_6 = "</listener-class>" + NL + "\t</listener>" + NL + "</xr:prepend>" + NL + "</xr:at>";
  protected final String TEXT_7 = NL + "</xr:refine>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	Vector<Model> models = (Vector<Model>) ((Object[])argument)[0];
	Vector<Facade> facades = (Vector<Facade>) ((Object[])argument)[1];

    stringBuffer.append(TEXT_2);
    
	for (Model m : models) {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_4);
    
	}
	for (Facade f : facades) {

    stringBuffer.append(TEXT_5);
    stringBuffer.append(f.getName());
    stringBuffer.append(TEXT_6);
    
	}

    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}

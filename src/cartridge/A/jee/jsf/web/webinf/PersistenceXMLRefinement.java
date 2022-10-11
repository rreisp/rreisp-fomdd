package cartridge.A.jee.jsf.web.webinf;

import core.mvc.model.*;
import java.util.Vector;

public class PersistenceXMLRefinement
{
  protected static String nl;
  public static synchronized PersistenceXMLRefinement create(String lineSeparator)
  {
    nl = lineSeparator;
    PersistenceXMLRefinement result = new PersistenceXMLRefinement();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
  protected final String TEXT_2 = NL + "<xr:refine xmlns:xr=\"http://www.atarix.org/xmlRefinement\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.atarix.org/xmlRefinement\">";
  protected final String TEXT_3 = NL + "<xr:at select=\"//persistence-unit\">" + NL + "<xr:prepend>" + NL + "\t<class>model.";
  protected final String TEXT_4 = "</class>" + NL + "</xr:prepend>" + NL + "</xr:at>" + NL;
  protected final String TEXT_5 = NL + "</xr:refine>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	Vector<Model> models = (Vector<Model>) ((Object[])argument)[0];

    stringBuffer.append(TEXT_2);
    
	for (Model m : models) {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_4);
    
	}

    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}

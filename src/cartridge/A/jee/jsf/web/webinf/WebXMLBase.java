package cartridge.A.jee.jsf.web.webinf;

import java.util.Vector;
import core.mvc.model.*;
import core.mvc.view.*;

public class WebXMLBase
{
  protected static String nl;
  public static synchronized WebXMLBase create(String lineSeparator)
  {
    nl = lineSeparator;
    WebXMLBase result = new WebXMLBase();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<web-app version=\"2.4\" xmlns:w=\"http://java.sun.com/xml/ns/j2ee\">" + NL + "  <display-name>";
  protected final String TEXT_2 = "</display-name>";
  protected final String TEXT_3 = NL + "    <listener>" + NL + "    \t<listener-class>model.";
  protected final String TEXT_4 = "</listener-class>" + NL + "    </listener>";
  protected final String TEXT_5 = NL + "    <listener>" + NL + "    \t<listener-class>model.";
  protected final String TEXT_6 = "Repository</listener-class>" + NL + "    </listener>";
  protected final String TEXT_7 = NL + " \t<servlet>" + NL + "\t\t<servlet-name>ControllerServlet</servlet-name>" + NL + "\t\t<servlet-class>controller.ControllerServlet</servlet-class>" + NL + "\t</servlet>" + NL + "\t" + NL + "\t<servlet-mapping>" + NL + "\t\t<servlet-name>ControllerServlet</servlet-name>" + NL + "\t\t<url-pattern>exe.cute</url-pattern>" + NL + "\t</servlet-mapping>" + NL + "</web-app>";
  protected final String TEXT_8 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	String productName = (String) ((Object[])argument)[0];
	Vector<Model> models = (Vector<Model>) ((Object[])argument)[1];
	Vector<Facade> facades = (Vector<Facade>) ((Object[])argument)[2];


    stringBuffer.append(TEXT_1);
    stringBuffer.append( productName.toLowerCase() );
    stringBuffer.append(TEXT_2);
     if (!facades.isEmpty()) { 
     for (Facade f : facades) { 	
    stringBuffer.append(TEXT_3);
    stringBuffer.append( f.getName() );
    stringBuffer.append(TEXT_4);
     }  } 
     if (!models.isEmpty()) { 
     for (Model m : models) { 	
    stringBuffer.append(TEXT_5);
    stringBuffer.append( m.getName());
    stringBuffer.append(TEXT_6);
     }  } 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}

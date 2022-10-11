package cartridge.A.jee.jsf.web;

import java.util.Vector;
import core.mvc.view.*;
import core.fieldtype.*;

public class JSPRefinement
{
  protected static String nl;
  public static synchronized JSPRefinement create(String lineSeparator)
  {
    nl = lineSeparator;
    JSPRefinement result = new JSPRefinement();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + NL + "<xr:refine xmlns:xr=\"http://www.atarix.org/xmlRefinement\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.atarix.org/xmlRefinement\"  xmlns:h=\"http://java.sun.com/jsf/html\" xmlns:f=\"http://java.sun.com/jsf/core\" xmlns:jsp=\"http://java.sun.com/JSP/Page\" version=\"2.0\">";
  protected final String TEXT_2 = NL + "<xr:at select=\"//*[@id='";
  protected final String TEXT_3 = "']\">" + NL + "<xr:append>";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "</xr:append>" + NL + "</xr:at>";
  protected final String TEXT_6 = NL + "</xr:refine>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	Vector<Element> elements = (Vector<Element>)((Object[])argument)[0];
	
	HTMLElementTranslator translator = new HTMLElementTranslator();
	String xmlContent;
	
	for (Element e : elements) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(e.getAt().replace(".", "") );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append( translator.translateBegin(e) + translator.translateEnd(e) );
    stringBuffer.append(TEXT_5);
    	} 
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}

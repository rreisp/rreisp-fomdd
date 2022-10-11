package cartridge.A.jee.jsf.web;

import cartridge.A.jee.jsf.*;
import java.util.*;
import java.io.*;
import core.mvc.view.*;
import core.fieldtype.*;
import core.scripts.ScriptUtils;;

public class JSPBase
{
  protected static String nl;
  public static synchronized JSPBase create(String lineSeparator)
  {
    nl = lineSeparator;
    JSPBase result = new JSPBase();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + NL + "<jsp:root version=\"1.2\" xmlns:jsp=\"http://java.sun.com/JSP/Page\">" + NL + "<jsp:output doctype-public=\"-//W3C//DTD HTML 4.01 Transitional//EN\" doctype-root-element=\"html\" doctype-system=\"http://www.w3.org/TR/html4/loose.dtd\"/>" + NL + "<jsp:directive.page contentType=\"text/html;charset=UTF-8\" language=\"java\" pageEncoding=\"UTF-8\" session=\"false\"/>" + NL + "<jsp:directive.page import=\"model.repository.*, model.*, java.util.List, java.util.ArrayList\"/>" + NL + "<html xmlns=\"http://www.w3.org/1999/xhtml\">" + NL + "<head>" + NL + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>" + NL + "<link rel=\"stylesheet\" href=\"";
  protected final String TEXT_2 = ".css\" type=\"text/css\"/>\t" + NL + "<title>::";
  protected final String TEXT_3 = "::</title>" + NL + "</head>" + NL + "<body>";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "</body>" + NL + "</html>\t\t" + NL + "</jsp:root>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Screen screen = (Screen) ((Object[])argument)[0];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(screen.getName().toLowerCase());
    stringBuffer.append(TEXT_2);
    stringBuffer.append( screen.getName() );
    stringBuffer.append(TEXT_3);
    
	Layout layout = new Layout();

    stringBuffer.append(TEXT_4);
    stringBuffer.append( layout.getText(screen.getLayout()) );
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}

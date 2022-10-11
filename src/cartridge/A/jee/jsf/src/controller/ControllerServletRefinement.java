package cartridge.A.jee.jsf.src.controller;

import java.util.List;
import java.util.Vector;
import core.mvc.controller.*;

public class ControllerServletRefinement
{
  protected static String nl;
  public static synchronized ControllerServletRefinement create(String lineSeparator)
  {
    nl = lineSeparator;
    ControllerServletRefinement result = new ControllerServletRefinement();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "layer controller";
  protected final String TEXT_2 = ";" + NL + "" + NL + "refines class ControllerServlet {" + NL + "" + NL + "\tpublic void init() {" + NL + "\t\tSuper().init();" + NL + "\t";
  protected final String TEXT_3 = "\tactions.put(\"goTo";
  protected final String TEXT_4 = "\", new GoTo";
  protected final String TEXT_5 = "Action());" + NL + "\t";
  protected final String TEXT_6 = "\tactions.put(\"";
  protected final String TEXT_7 = "\", new ";
  protected final String TEXT_8 = "Action());" + NL + "\t";
  protected final String TEXT_9 = "\tactions.put(\"";
  protected final String TEXT_10 = "\", new ";
  protected final String TEXT_11 = "Action());" + NL + "\t";
  protected final String TEXT_12 = "\t \t \t  " + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	String screen = (String) ((Object[])argument)[0];
	List<String> handlerGuards = (List<String>) ((Object[])argument)[1];
	List<String> handlerActions = (List<String>) ((Object[])argument)[2];
	String feature = (String) ((Object[])argument)[3];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(feature);
    stringBuffer.append(TEXT_2);
    
	  	if (screen.compareTo("") != 0) {
	
    stringBuffer.append(TEXT_3);
    stringBuffer.append(screen);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(screen);
    stringBuffer.append(TEXT_5);
    	
		}	
	
    
	  	for (String guard : handlerGuards) {
	
    stringBuffer.append(TEXT_6);
    stringBuffer.append(guard.substring(0, 1).toLowerCase() + guard.substring(1));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(guard);
    stringBuffer.append(TEXT_8);
    	
		}	
	
    
	  	for (String action : handlerActions) {
	
    stringBuffer.append(TEXT_9);
    stringBuffer.append(action.substring(0, 1).toLowerCase() + action.substring(1));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(action);
    stringBuffer.append(TEXT_11);
    	
		}	
	
    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
}

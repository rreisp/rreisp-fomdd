package cartridge.A.jee.jsf.src.controller;

import java.util.List;
import java.util.Vector;
import core.mvc.controller.*;

public class ControllerServletBase
{
  protected static String nl;
  public static synchronized ControllerServletBase create(String lineSeparator)
  {
    nl = lineSeparator;
    ControllerServletBase result = new ControllerServletBase();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "layer controller;" + NL + "" + NL + "import java.io.IOException;" + NL + "import java.util.HashMap;" + NL + "import java.util.Map;" + NL + "import javax.servlet.ServletException;" + NL + "import javax.servlet.http.HttpServlet;" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "import javax.servlet.http.HttpServletResponse;" + NL + "import controller.action.*;" + NL + "" + NL + "public class ControllerServlet extends HttpServlet {" + NL + "" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL + "\tprivate Map actions = new HashMap();" + NL + "\tprivate static final String ACTION_IDENTIFIER = \"action\";" + NL + "" + NL + "\tpublic void init() {" + NL + "\t\t// TODO This part you'll need to write by hand. Sorry!" + NL + "\t\t// Action classes must be coded by hand and implement the \"controller.ControllerAction\" interface." + NL + "\t\t// Follow the pattern below to include all your custom actions:" + NL + "\t\t// actions.put(\"goToHome\", new GoToHome());" + NL + "\t";
  protected final String TEXT_2 = "\tactions.put(\"goTo";
  protected final String TEXT_3 = "\", new GoTo";
  protected final String TEXT_4 = "Action());" + NL + "\t";
  protected final String TEXT_5 = "\tactions.put(\"";
  protected final String TEXT_6 = "\", new ";
  protected final String TEXT_7 = "Action());" + NL + "\t";
  protected final String TEXT_8 = "\tactions.put(\"";
  protected final String TEXT_9 = "\", new ";
  protected final String TEXT_10 = "Action());" + NL + "\t";
  protected final String TEXT_11 = "\t \t \t  " + NL + "\t}" + NL + "" + NL + "\tpublic void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "" + NL + "\t\tString actionRequestParameter = request.getParameter(ACTION_IDENTIFIER);" + NL + "" + NL + "\t\tif (null == actionRequestParameter) {" + NL + "\t\t\tactionRequestParameter = \"goToHome\";" + NL + "\t\t}" + NL + "" + NL + "\t\tControllerAction command = (ControllerAction) actions.get(actionRequestParameter);" + NL + "" + NL + "\t\tif (null == command) {" + NL + "\t\t\tthrow new IllegalArgumentException(\"No command for form action: \" + actionRequestParameter);" + NL + "\t\t}" + NL + "\t\tcommand.execute(request, response);" + NL + "\t}" + NL + "" + NL + "\tpublic void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\tprocessAction(request, response);" + NL + "\t}" + NL + "" + NL + "\tpublic void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\tprocessAction(request, response);" + NL + "\t}" + NL + "}";
  protected final String TEXT_12 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	List<Screen> screens = (List<Screen>) ((Object[])argument)[0];
	List<String> handlerGuards = (List<String>) ((Object[])argument)[1];
	List<String> handlerActions = (List<String>) ((Object[])argument)[2];

    stringBuffer.append(TEXT_1);
    
	  	for (Screen goToScreen : screens) {
	
    stringBuffer.append(TEXT_2);
    stringBuffer.append(goToScreen.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(goToScreen.getName());
    stringBuffer.append(TEXT_4);
    	
		}	
	
    
	  	for (String guard : handlerGuards) {
	
    stringBuffer.append(TEXT_5);
    stringBuffer.append(guard.substring(0, 1).toLowerCase() + guard.substring(1));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(guard);
    stringBuffer.append(TEXT_7);
    	
		}	
	
    
	  	for (String action : handlerActions) {
	
    stringBuffer.append(TEXT_8);
    stringBuffer.append(action.substring(0, 1).toLowerCase() + action.substring(1));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(action);
    stringBuffer.append(TEXT_10);
    	
		}	
	
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
}

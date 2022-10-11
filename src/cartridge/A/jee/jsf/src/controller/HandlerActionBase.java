package cartridge.A.jee.jsf.src.controller;

import java.util.Vector;

public class HandlerActionBase
{
  protected static String nl;
  public static synchronized HandlerActionBase create(String lineSeparator)
  {
    nl = lineSeparator;
    HandlerActionBase result = new HandlerActionBase();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "layer controller.action;" + NL + "" + NL + "import javax.servlet.ServletContext;" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "import javax.servlet.http.HttpServletResponse;" + NL + "import javax.servlet.http.HttpSession;" + NL + "import model.repository.*;" + NL + "import model.action.*;" + NL + "import model.*;" + NL + "" + NL + "public class ";
  protected final String TEXT_2 = "Action implements ControllerAction {" + NL + "" + NL + "    @Override" + NL + "    public void execute(HttpServletRequest request, HttpServletResponse response) {" + NL + "\t\tServletContext context = request.getSession().getServletContext();" + NL + "\t\ttry {" + NL + "   " + NL + "\t\t\t// TODO This part you'll need to write by hand. Sorry!" + NL + "\t\t\t// Do all processing steps (save, update)  and forward to another GoToSomewhere action (through 'execute' method)" + NL + "\t" + NL + "\t\t} catch (Exception e) {" + NL + "\t \t   e.printStackTrace();" + NL + "\t\t}" + NL + "\t" + NL + "\t}" + NL + "}";
  protected final String TEXT_3 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	String action = (String) ((Object[])argument)[0];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(action);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}

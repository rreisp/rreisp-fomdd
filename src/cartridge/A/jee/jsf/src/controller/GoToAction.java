package cartridge.A.jee.jsf.src.controller;

import java.util.Vector;
import core.mvc.controller.*;

public class GoToAction
{
  protected static String nl;
  public static synchronized GoToAction create(String lineSeparator)
  {
    nl = lineSeparator;
    GoToAction result = new GoToAction();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "layer controller.action;" + NL + "" + NL + "import javax.servlet.ServletContext;" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "import javax.servlet.http.HttpServletResponse;" + NL + "" + NL + "public class GoTo";
  protected final String TEXT_2 = "Action implements ControllerAction {" + NL + "" + NL + "    @Override" + NL + "    public void execute(HttpServletRequest request, HttpServletResponse response) {" + NL + "\t\tServletContext context = request.getSession().getServletContext();" + NL + "\t\ttry {" + NL + "\t\t    context.getRequestDispatcher(\"/";
  protected final String TEXT_3 = ".jsp\").forward(request, response);" + NL + "\t\t} catch (Exception e) {" + NL + "\t \t   e.printStackTrace();" + NL + "\t\t}" + NL + "    }" + NL + "}";
  protected final String TEXT_4 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Screen goToScreen = (Screen) ((Object[])argument)[0];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(goToScreen.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(goToScreen.getName().toLowerCase());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}

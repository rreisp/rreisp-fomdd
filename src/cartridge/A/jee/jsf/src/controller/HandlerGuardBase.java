package cartridge.A.jee.jsf.src.controller;

import java.util.Vector;
import core.mvc.view.*;

public class HandlerGuardBase
{
  protected static String nl;
  public static synchronized HandlerGuardBase create(String lineSeparator)
  {
    nl = lineSeparator;
    HandlerGuardBase result = new HandlerGuardBase();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "layer controller.action;" + NL + "" + NL + "import javax.servlet.ServletContext;" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "import javax.servlet.http.HttpServletResponse;" + NL + "import javax.servlet.http.HttpSession;" + NL + "import model.repository.*;" + NL + "import model.action.*;" + NL + "import model.*;" + NL + "" + NL + "public class ";
  protected final String TEXT_2 = "Action implements ControllerAction {" + NL + "" + NL + "    @Override" + NL + "    public void execute(HttpServletRequest request, HttpServletResponse response) {" + NL + "\t\ttry {" + NL + "\t\t\tServletContext context = request.getSession().getServletContext();" + NL + "\t\t\t" + NL + "\t\t\t// TODO This part you'll need to write by hand. Sorry!" + NL + "\t\t\t// Do all processing steps (save, update) here" + NL + "\t\t\t" + NL + "\t\t} catch (Exception e) {" + NL + "\t\t\te.printStackTrace();" + NL + "\t\t}" + NL + "\t}" + NL + "}";
  protected final String TEXT_3 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	String guardName = (String) ((Object[])argument)[0];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(guardName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}

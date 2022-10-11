package cartridge.A.jee.jsf.src.controller;

public class ControllerAction
{
  protected static String nl;
  public static synchronized ControllerAction create(String lineSeparator)
  {
    nl = lineSeparator;
    ControllerAction result = new ControllerAction();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "layer controller.action;" + NL + "" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "import javax.servlet.http.HttpServletResponse;" + NL + "" + NL + "public interface ControllerAction {" + NL + "\tvoid execute(HttpServletRequest request, HttpServletResponse response);" + NL + "}";
  protected final String TEXT_2 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}

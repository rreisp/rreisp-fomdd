package cartridge.A.jee.jsf.src.model;

public class ModelAction
{
  protected static String nl;
  public static synchronized ModelAction create(String lineSeparator)
  {
    nl = lineSeparator;
    ModelAction result = new ModelAction();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "layer model.action;" + NL + "" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "import javax.servlet.http.HttpServletResponse;" + NL + "" + NL + "public interface ModelAction {" + NL + "" + NL + "    Object execute(HttpServletRequest request, HttpServletResponse response);" + NL + "" + NL + "    void setParams(HttpServletRequest request);" + NL + "}";
  protected final String TEXT_2 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}

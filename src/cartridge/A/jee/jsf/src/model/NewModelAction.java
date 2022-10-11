package cartridge.A.jee.jsf.src.model;

import core.mvc.model.*;

public class NewModelAction
{
  protected static String nl;
  public static synchronized NewModelAction create(String lineSeparator)
  {
    nl = lineSeparator;
    NewModelAction result = new NewModelAction();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "layer model.action;" + NL + "" + NL + "import javax.servlet.ServletContext;" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "import javax.servlet.http.HttpServletResponse;" + NL + "import java.util.List;" + NL + "" + NL + "import model.";
  protected final String TEXT_2 = ";" + NL + "import model.repository.";
  protected final String TEXT_3 = "Repository;" + NL + "" + NL + "public class New";
  protected final String TEXT_4 = "Action implements ModelAction {" + NL + "" + NL + "    Object params[];" + NL + "    private ";
  protected final String TEXT_5 = "Repository ";
  protected final String TEXT_6 = "Repository;" + NL + "" + NL + "    public New";
  protected final String TEXT_7 = "Action() {" + NL + "    " + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public Object execute(HttpServletRequest request, HttpServletResponse response) {" + NL + "        ServletContext context = request.getSession().getServletContext();";
  protected final String TEXT_8 = NL + "        ";
  protected final String TEXT_9 = "Repository = (";
  protected final String TEXT_10 = "Repository) context.getAttribute(\"";
  protected final String TEXT_11 = "Repository\");" + NL + "        setParams(request);" + NL + "" + NL + "        // TODO This part you'll need to write by hand. Sorry!" + NL + "        // Create (Persist) your ";
  protected final String TEXT_12 = " using ";
  protected final String TEXT_13 = "Repository";
  protected final String TEXT_14 = NL + "        ";
  protected final String TEXT_15 = " object = ";
  protected final String TEXT_16 = "Repository.new";
  protected final String TEXT_17 = "();" + NL + "\t\t" + NL + "        // return your persistent Entity;" + NL + "        return object;" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public void setParams(HttpServletRequest request) {" + NL + "        // TODO This part you'll need to write by hand. Sorry!" + NL + "        // Get the ";
  protected final String TEXT_18 = " fields from request and put in params[] array" + NL + "\t\t" + NL + "    }" + NL + "}";
  protected final String TEXT_19 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Model m = (Model)((Object[])argument)[0];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(m.getName().toLowerCase());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(m.getName().toLowerCase());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(m.getName().toLowerCase());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(m.getName().toLowerCase());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(m.getName().toLowerCase());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(m.getName().toLowerCase());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(m.getName().toLowerCase());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    return stringBuffer.toString();
  }
}

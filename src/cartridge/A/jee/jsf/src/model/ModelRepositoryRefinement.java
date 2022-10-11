package cartridge.A.jee.jsf.src.model;

import java.util.Vector;
import core.mvc.model.*;

public class ModelRepositoryRefinement
{
  protected static String nl;
  public static synchronized ModelRepositoryRefinement create(String lineSeparator)
  {
    nl = lineSeparator;
    ModelRepositoryRefinement result = new ModelRepositoryRefinement();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "layer model";
  protected final String TEXT_2 = ";" + NL + "" + NL + "" + NL + "refines class ";
  protected final String TEXT_3 = "Repository {" + NL + "" + NL + "   public ";
  protected final String TEXT_4 = " new";
  protected final String TEXT_5 = "(";
  protected final String TEXT_6 = "List ";
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = ", ";
  protected final String TEXT_9 = ") {" + NL + "\t\t";
  protected final String TEXT_10 = " object = new ";
  protected final String TEXT_11 = "(";
  protected final String TEXT_12 = ", ";
  protected final String TEXT_13 = ");" + NL + "\t\tpersistOrMerge(object, object.getId());" + NL + "\t\treturn object;" + NL + "\t}\t" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Model m = (Model) ((Object[])argument)[0];
	Vector<Attribute> atts = (Vector<Attribute>)((Object[])argument)[1];
	String feature = (String)((Object[])argument)[2];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(feature);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_5);
     for(Attribute a : atts) { if (a.getFeature().compareTo("") != 0) { if (a.getMetaType() != null && a.getMetaType().equals("OneToMany")) { 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(a.getName());
     } else { 
    stringBuffer.append(a.getType());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(a.getName());
    	} if (a.getName() != ((Attribute) atts.lastElement()).getName()) { 
    stringBuffer.append(TEXT_8);
     } } } 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_11);
     for(Attribute a : atts) { if (a.getFeature().compareTo("") != 0) { 
    stringBuffer.append(a.getName());
     if (a.getName() != ((Attribute) atts.lastElement()).getName()) { 
    stringBuffer.append(TEXT_12);
     } } } 
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}

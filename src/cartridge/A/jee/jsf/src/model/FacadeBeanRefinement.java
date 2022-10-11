package cartridge.A.jee.jsf.src.model;

import java.util.Vector;
import core.mvc.model.*;

public class FacadeBeanRefinement
{
  protected static String nl;
  public static synchronized FacadeBeanRefinement create(String lineSeparator)
  {
    nl = lineSeparator;
    FacadeBeanRefinement result = new FacadeBeanRefinement();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "layer model";
  protected final String TEXT_2 = ";" + NL + "" + NL + "refines class ";
  protected final String TEXT_3 = "Facade {" + NL;
  protected final String TEXT_4 = "\t" + NL + "\tprivate ";
  protected final String TEXT_5 = " ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL + NL + "\tpublic void ";
  protected final String TEXT_8 = "FacadeConstructor(Object[] o){";
  protected final String TEXT_9 = NL + "\t\tSuper(Object[]).";
  protected final String TEXT_10 = "Constructor(o);";
  protected final String TEXT_11 = NL + "\t\tthis.";
  protected final String TEXT_12 = " = (";
  protected final String TEXT_13 = ") o[";
  protected final String TEXT_14 = "];";
  protected final String TEXT_15 = "\t\t" + NL + "\t}";
  protected final String TEXT_16 = "\t" + NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_17 = " get";
  protected final String TEXT_18 = "(){ return this.";
  protected final String TEXT_19 = "; }" + NL + "\t" + NL + "\tpublic void set";
  protected final String TEXT_20 = "(";
  protected final String TEXT_21 = " ";
  protected final String TEXT_22 = "){" + NL + "\t\tthis.";
  protected final String TEXT_23 = " = ";
  protected final String TEXT_24 = ";" + NL + "\t}";
  protected final String TEXT_25 = NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_26 = " ";
  protected final String TEXT_27 = "(";
  protected final String TEXT_28 = ", ";
  protected final String TEXT_29 = ")" + NL + "\t{" + NL + "\t\t// TODO" + NL + "\t}";
  protected final String TEXT_30 = NL + "}" + NL + NL;
  protected final String TEXT_31 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Facade f = (Facade)((Object[])argument)[0];
	Vector<Attribute> atts = (Vector<Attribute>)((Object[])argument)[1];
	Vector<Operation> ops = (Vector<Operation>)((Object[])argument)[2];
	Vector<Attribute> parameterStack = (Vector<Attribute>)((Object[]) argument)[3];
	String feature = (String) ((Object[])argument)[4];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(feature);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(f.getName() );
    stringBuffer.append(TEXT_3);
    
	for(Attribute a: atts){
		if(a.getFeature().compareTo("")!=0 && a.getFeature() != null){

    stringBuffer.append(TEXT_4);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_6);
    
		}
	}
	
	if(atts.size()>0){

    stringBuffer.append(TEXT_7);
    stringBuffer.append(f.getName() );
    stringBuffer.append(TEXT_8);
    
	int i = parameterStack.size();
	if(i != 0){

    stringBuffer.append(TEXT_9);
    stringBuffer.append(f.getName() );
    stringBuffer.append(TEXT_10);
    
	}

	for(Attribute a: atts){

    stringBuffer.append(TEXT_11);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_13);
    stringBuffer.append(i );
    stringBuffer.append(TEXT_14);
    
		parameterStack.add(a);
		i++;
	}

    stringBuffer.append(TEXT_15);
    
	}

	for(Attribute a: atts){
		String attrName = (""+ a.getName().charAt(0)).toUpperCase() + a.getName().substring(1,a.getName().length());

    stringBuffer.append(TEXT_16);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_17);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_21);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_24);
    
	}
	
	for(Operation o: ops){
		Vector<Parameter> parameters = o.getParameters();

    stringBuffer.append(TEXT_25);
    stringBuffer.append(o.getType() );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(o.getName() );
    stringBuffer.append(TEXT_27);
     for(Parameter p: parameters){ 
    stringBuffer.append(p.getType() +" "+ p.getName() );
    if(p.getName() != ((Parameter) parameters.lastElement()).getName()){ 
    stringBuffer.append(TEXT_28);
    } }
    stringBuffer.append(TEXT_29);
    
	}

    stringBuffer.append(TEXT_30);
    stringBuffer.append(TEXT_31);
    return stringBuffer.toString();
  }
}

package cartridge.A.jee.jsf.src.model;

import java.util.Vector;
import core.mvc.model.*;

public class ModelBeanRefinement
{
  protected static String nl;
  public static synchronized ModelBeanRefinement create(String lineSeparator)
  {
    nl = lineSeparator;
    ModelBeanRefinement result = new ModelBeanRefinement();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "layer model";
  protected final String TEXT_2 = ";" + NL + "" + NL + "refines class ";
  protected final String TEXT_3 = " {" + NL;
  protected final String TEXT_4 = NL + "\t@OneToOne(mappedBy=";
  protected final String TEXT_5 = ".Attribute.";
  protected final String TEXT_6 = ")" + NL + "\tprivate ";
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = ";" + NL + "\t";
  protected final String TEXT_9 = NL + "\t@OneToOne" + NL + "\tprivate ";
  protected final String TEXT_10 = " ";
  protected final String TEXT_11 = ";" + NL + "\t";
  protected final String TEXT_12 = NL + "\t@OneToMany(targetEntity = ";
  protected final String TEXT_13 = ".class, mappedBy = ";
  protected final String TEXT_14 = ".Attribute.";
  protected final String TEXT_15 = ", fetch = FetchType.LAZY)" + NL + "\tprivate List ";
  protected final String TEXT_16 = ";" + NL + "\t";
  protected final String TEXT_17 = NL + "\t@ManyToOne(targetEntity = ";
  protected final String TEXT_18 = ".class, fetch = FetchType.LAZY)" + NL + "\tprivate ";
  protected final String TEXT_19 = " ";
  protected final String TEXT_20 = ";" + NL + "\t";
  protected final String TEXT_21 = NL + "\tprivate ";
  protected final String TEXT_22 = " ";
  protected final String TEXT_23 = ";" + NL + "\t";
  protected final String TEXT_24 = "\t" + NL + "\tpublic List get";
  protected final String TEXT_25 = "() { ";
  protected final String TEXT_26 = "\t" + NL + "\tpublic ";
  protected final String TEXT_27 = " get";
  protected final String TEXT_28 = "() { ";
  protected final String TEXT_29 = "\t" + NL + "\t\treturn this.";
  protected final String TEXT_30 = ";" + NL + "\t}" + NL + "\t";
  protected final String TEXT_31 = NL + "\tpublic void set";
  protected final String TEXT_32 = "(List ";
  protected final String TEXT_33 = ") {";
  protected final String TEXT_34 = NL + "\tpublic void set";
  protected final String TEXT_35 = "(";
  protected final String TEXT_36 = " ";
  protected final String TEXT_37 = ") {";
  protected final String TEXT_38 = "\t" + NL + "\t\tthis.";
  protected final String TEXT_39 = " = ";
  protected final String TEXT_40 = ";" + NL + "\t}" + NL + "\t";
  protected final String TEXT_41 = "\t" + NL + "\tpublic ";
  protected final String TEXT_42 = " ";
  protected final String TEXT_43 = "(";
  protected final String TEXT_44 = ", ";
  protected final String TEXT_45 = ")\t{" + NL + "" + NL + "\t}";
  protected final String TEXT_46 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Model m = (Model)((Object[])argument)[0];
	Vector<Attribute> atts = (Vector<Attribute>)((Object[])argument)[1];
	Vector<Operation> ops = (Vector<Operation>)((Object[])argument)[2];
	Vector<Attribute> parameterStack = (Vector<Attribute>)((Object[]) argument)[3];
	String feature = (String) ((Object[])argument)[4];
	
	String upperName = m.getName();
	upperName = upperName.toUpperCase();
	


    stringBuffer.append(TEXT_1);
    stringBuffer.append(feature);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(m.getName() );
    stringBuffer.append(TEXT_3);
    
	for (Attribute a : atts) {
		if (a.getMetaType() != null) {
	   	if (a.getFeature() != null && a.getFeature().compareTo("") != 0) {
	   		if (a.getMetaType().compareTo("OneToOne") == 0) {
	   			if (a.getDirection().equals("src")) {
	
    stringBuffer.append(TEXT_4);
    stringBuffer.append( a.getType() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append( upperName );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_8);
    
					}
					else if (a.getDirection().compareTo("dst") == 0) {
	
    stringBuffer.append(TEXT_9);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_10);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_11);
    
					}
				}
				else if (a.getMetaType().compareTo("OneToMany")  == 0) {
	
    stringBuffer.append(TEXT_12);
    stringBuffer.append( a.getType() );
    stringBuffer.append(TEXT_13);
    stringBuffer.append( a.getType() );
    stringBuffer.append(TEXT_14);
    stringBuffer.append( upperName );
    stringBuffer.append(TEXT_15);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_16);
    
				}
				else if (a.getMetaType().compareTo("ManyToOne")  == 0) {
	
    stringBuffer.append(TEXT_17);
    stringBuffer.append( a.getType() );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_20);
    
				}
			}
		}
		else {
	
    stringBuffer.append(TEXT_21);
    stringBuffer.append(a.getType());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_23);
    
		}
	}

    
	for (Attribute a : atts){
		String attrName = (""+ a.getName().charAt(0)).toUpperCase() + a.getName().substring(1,a.getName().length());
		if (a.getMetaType() != null && a.getMetaType().equals("OneToMany")) {

    stringBuffer.append(TEXT_24);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_25);
    
	}
	else {

    stringBuffer.append(TEXT_26);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_27);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_28);
    
	}

    stringBuffer.append(TEXT_29);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_30);
    
		if (a.getMetaType() != null && a.getMetaType().equals("OneToMany")) {

    stringBuffer.append(TEXT_31);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_32);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_33);
    
	}
	else {

    stringBuffer.append(TEXT_34);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_35);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_36);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_37);
    
	}

    stringBuffer.append(TEXT_38);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_39);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_40);
    
	}
	
	for(Operation o: ops){
		Vector<Parameter> parameters = o.getParameters();

    stringBuffer.append(TEXT_41);
    stringBuffer.append(o.getType() );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(o.getName() );
    stringBuffer.append(TEXT_43);
     for(Parameter p: parameters){ 
    stringBuffer.append(p.getType() +" "+ p.getName());
     
	if (p.getName() != ((Parameter) parameters.lastElement()).getName()){ 
    stringBuffer.append(TEXT_44);
    } }
    stringBuffer.append(TEXT_45);
    
	}

    stringBuffer.append(TEXT_46);
    return stringBuffer.toString();
  }
}

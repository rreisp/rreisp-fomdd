package cartridge.A.jee.jsf.src.model;

import java.util.Vector;
import core.mvc.model.*;

public class ModelBeanBase
{
  protected static String nl;
  public static synchronized ModelBeanBase create(String lineSeparator)
  {
    nl = lineSeparator;
    ModelBeanBase result = new ModelBeanBase();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "layer model;" + NL + "" + NL + "import java.io.Serializable;" + NL + "import java.util.List;" + NL + "import javax.persistence.*;" + NL + "" + NL + "" + NL + "@Entity" + NL + "@Table(name = \"";
  protected final String TEXT_2 = "\")" + NL + "public class ";
  protected final String TEXT_3 = " implements java.io.Serializable {" + NL + "" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL + "\t" + NL + "\tpublic static class Attribute {" + NL + "\t";
  protected final String TEXT_4 = "\tpublic final static String ";
  protected final String TEXT_5 = " = \"";
  protected final String TEXT_6 = "\";" + NL + "\t";
  protected final String TEXT_7 = "\tpublic final static String ";
  protected final String TEXT_8 = " = \"";
  protected final String TEXT_9 = "\";" + NL + "\t";
  protected final String TEXT_10 = "\t// TODO: This part you'll need to write by hand. Sorry!" + NL + "\t\t// Complete with more relation attributes that aren't listed here" + NL + "\t\t// Composed attributes need to be added." + NL + "\t}" + NL + "\t" + NL + "\t@Id" + NL + "\t@GeneratedValue" + NL + "\tprivate Long id;" + NL + "\t";
  protected final String TEXT_11 = NL + "\t@OneToOne(mappedBy=";
  protected final String TEXT_12 = ".Attribute.";
  protected final String TEXT_13 = ")" + NL + "\tprivate ";
  protected final String TEXT_14 = " ";
  protected final String TEXT_15 = ";" + NL + "\t" + NL + "\t";
  protected final String TEXT_16 = NL + "\t@OneToOne" + NL + "\tprivate ";
  protected final String TEXT_17 = " ";
  protected final String TEXT_18 = ";" + NL + "\t" + NL + "\t";
  protected final String TEXT_19 = NL + "\t@OneToMany(targetEntity = ";
  protected final String TEXT_20 = ".class, mappedBy = ";
  protected final String TEXT_21 = ".Attribute.";
  protected final String TEXT_22 = ", fetch = FetchType.LAZY)" + NL + "\tprivate List ";
  protected final String TEXT_23 = ";" + NL + "\t" + NL + "\t";
  protected final String TEXT_24 = NL + "\t@ManyToOne(targetEntity = ";
  protected final String TEXT_25 = ".class, fetch = FetchType.LAZY)" + NL + "\tprivate ";
  protected final String TEXT_26 = " ";
  protected final String TEXT_27 = ";" + NL + "\t" + NL + "\t";
  protected final String TEXT_28 = NL + "\tprivate ";
  protected final String TEXT_29 = " ";
  protected final String TEXT_30 = ";" + NL + "\t";
  protected final String TEXT_31 = NL + "\tpublic ";
  protected final String TEXT_32 = "() {" + NL + "\t\t// Used by JPA." + NL + "\t}" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_33 = "(";
  protected final String TEXT_34 = "List ";
  protected final String TEXT_35 = " ";
  protected final String TEXT_36 = ", ";
  protected final String TEXT_37 = ") {";
  protected final String TEXT_38 = NL + "\t\tthis.";
  protected final String TEXT_39 = " = (List) ";
  protected final String TEXT_40 = ";\t";
  protected final String TEXT_41 = NL + "\t\tthis.";
  protected final String TEXT_42 = " = (";
  protected final String TEXT_43 = ") ";
  protected final String TEXT_44 = ";";
  protected final String TEXT_45 = "\t\t" + NL + "\t}" + NL + "\t" + NL + "\tpublic void setId(Long id) {" + NL + "\t\tthis.id = id;" + NL + "\t}" + NL + "" + NL + "\tpublic Long getId() {" + NL + "\t\treturn id;" + NL + "\t}" + NL + "\t";
  protected final String TEXT_46 = "\t" + NL + "\tpublic List get";
  protected final String TEXT_47 = "() { ";
  protected final String TEXT_48 = "\t" + NL + "\tpublic ";
  protected final String TEXT_49 = " get";
  protected final String TEXT_50 = "() { ";
  protected final String TEXT_51 = "\t" + NL + "\t\treturn this.";
  protected final String TEXT_52 = ";" + NL + "\t}" + NL + "\t";
  protected final String TEXT_53 = NL + "\tpublic void set";
  protected final String TEXT_54 = "(List ";
  protected final String TEXT_55 = ") {";
  protected final String TEXT_56 = NL + "\tpublic void set";
  protected final String TEXT_57 = "(";
  protected final String TEXT_58 = " ";
  protected final String TEXT_59 = ") {";
  protected final String TEXT_60 = "\t" + NL + "\t\tthis.";
  protected final String TEXT_61 = " = ";
  protected final String TEXT_62 = ";" + NL + "\t}" + NL + "\t";
  protected final String TEXT_63 = "\t" + NL + "\tpublic ";
  protected final String TEXT_64 = " ";
  protected final String TEXT_65 = "(";
  protected final String TEXT_66 = ", ";
  protected final String TEXT_67 = ")\t{" + NL + "" + NL + "\t}";
  protected final String TEXT_68 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Model m = (Model)((Object[])argument)[0];
	Vector<Attribute> atts = (Vector<Attribute>)((Object[])argument)[1];
	Vector<Operation> ops = (Vector<Operation>)((Object[])argument)[2];
	
	String upperName = m.getName();
	upperName = upperName.toUpperCase();	

    stringBuffer.append(TEXT_1);
    stringBuffer.append( "t_" + m.getName().toLowerCase() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append(m.getName() );
    stringBuffer.append(TEXT_3);
    
	   for (Attribute a : atts) {
	   	if (a.getMetaType() != null) {
	   		if (a.getMetaType().compareTo("") != 0 && a.getMetaType().compareTo("Primitive") != 0) {
	
    stringBuffer.append(TEXT_4);
    stringBuffer.append( a.getType().toUpperCase() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append( a.getName() );
    stringBuffer.append(TEXT_6);
    
				}
				else {
	
    stringBuffer.append(TEXT_7);
    stringBuffer.append( a.getName() .toUpperCase() );
    stringBuffer.append(TEXT_8);
    stringBuffer.append( a.getName() );
    stringBuffer.append(TEXT_9);
    	
				}
			}
		}
	
    stringBuffer.append(TEXT_10);
    
	for (Attribute a : atts) {
	   if (a.getMetaType() != null  && a.getDirection() != null) {
	   	if (a.getMetaType().equals("OneToOne")) {
	   		if (a.getDirection().compareTo("src") != 0) {
	
    stringBuffer.append(TEXT_11);
    stringBuffer.append( a.getType() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append( upperName );
    stringBuffer.append(TEXT_13);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_14);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_15);
    
			}
			else if (a.getDirection().compareTo("dst") != 0) {
	
    stringBuffer.append(TEXT_16);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_17);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_18);
    
			}
		}
		else if (a.getMetaType().equals("OneToMany")) {
	
    stringBuffer.append(TEXT_19);
    stringBuffer.append( a.getType() );
    stringBuffer.append(TEXT_20);
    stringBuffer.append( a.getType() );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( upperName );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_23);
    
		}
		else if (a.getMetaType().equals("ManyToOne")) {
	
    stringBuffer.append(TEXT_24);
    stringBuffer.append( a.getType() );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_27);
    	
		}
		}
		else {
	
    stringBuffer.append(TEXT_28);
    stringBuffer.append(a.getType());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_30);
    
		}
	}
	
    stringBuffer.append(TEXT_31);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_33);
     for(Attribute a : atts) { if (a.getMetaType() != null && a.getMetaType().equals("OneToMany")) { 
    stringBuffer.append(TEXT_34);
    stringBuffer.append(a.getName());
     } else { 
    stringBuffer.append(a.getType());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(a.getName());
     } if (a.getName() != ((Attribute) atts.lastElement()).getName()) { 
    stringBuffer.append(TEXT_36);
     } } 
    stringBuffer.append(TEXT_37);
    
	for (Attribute a : atts) {
		if (a.getMetaType() != null && a.getMetaType().equals("OneToMany")) {

    stringBuffer.append(TEXT_38);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_40);
    
		}
		else {

    stringBuffer.append(TEXT_41);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(a.getType());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(a.getName());
    stringBuffer.append(TEXT_44);
    
		}
	}

    stringBuffer.append(TEXT_45);
    
	for (Attribute a : atts){
		String attrName = (""+ a.getName().charAt(0)).toUpperCase() + a.getName().substring(1,a.getName().length());
		if (a.getMetaType() != null && a.getMetaType().equals("OneToMany")) {

    stringBuffer.append(TEXT_46);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_47);
    
	}
	else {

    stringBuffer.append(TEXT_48);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_50);
    
	}

    stringBuffer.append(TEXT_51);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_52);
    
		if (a.getMetaType() != null && a.getMetaType().equals("OneToMany")) {

    stringBuffer.append(TEXT_53);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_54);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_55);
    
	}
	else {

    stringBuffer.append(TEXT_56);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_58);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_59);
    
	}

    stringBuffer.append(TEXT_60);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_61);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_62);
    
	}

	for(Operation o: ops){
		Vector<Parameter> parameters = o.getParameters();

    stringBuffer.append(TEXT_63);
    stringBuffer.append(o.getType() );
    stringBuffer.append(TEXT_64);
    stringBuffer.append(o.getName() );
    stringBuffer.append(TEXT_65);
     for(Parameter p: parameters){ 
    stringBuffer.append(p.getType() +" "+ p.getName());
     
	if (p.getName() != ((Parameter) parameters.lastElement()).getName()){ 
    stringBuffer.append(TEXT_66);
    } }
    stringBuffer.append(TEXT_67);
    
	}

    stringBuffer.append(TEXT_68);
    return stringBuffer.toString();
  }
}

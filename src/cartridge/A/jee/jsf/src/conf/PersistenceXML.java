package cartridge.A.jee.jsf.src.conf;

import java.util.Vector;
import core.mvc.model.*;

public class PersistenceXML
{
  protected static String nl;
  public static synchronized PersistenceXML create(String lineSeparator)
  {
    nl = lineSeparator;
    PersistenceXML result = new PersistenceXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<persistence xmlns:p=\"http://java.sun.com/xml/ns/persistence\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd\" version=\"1.0\">" + NL + "\t<persistence-unit name=\"store-pu\" transaction-type=\"RESOURCE_LOCAL\">" + NL + "\t\t<non-jta-data-source>java:DefaultDS</non-jta-data-source>";
  protected final String TEXT_2 = NL + "\t\t<class>model.";
  protected final String TEXT_3 = "</class>";
  protected final String TEXT_4 = NL + "\t\t<properties>" + NL + "\t\t\t<property name=\"hibernate.dialect\" value=\"org.hibernate.dialect.HSQLDialect\" />" + NL + "\t\t\t<property name=\"hibernate.hbm2ddl.auto\" value=\"update\" />" + NL + "\t\t\t<property name=\"hibernate.show_sql\" value=\"true\" />" + NL + "\t\t</properties>" + NL + "\t</persistence-unit>" + NL + "</persistence>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	String productName = (String) ((Object[])argument)[0];
	Vector<Model> models = (Vector<Model>) ((Object[])argument)[1];

    stringBuffer.append(TEXT_1);
    
	for (Model m : models) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_3);
    
	}

    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}

package cartridge.A.jee.jsf.src.model;

import java.util.Vector;
import core.mvc.model.*;

public class FacadeBeanBase
{
  protected static String nl;
  public static synchronized FacadeBeanBase create(String lineSeparator)
  {
    nl = lineSeparator;
    FacadeBeanBase result = new FacadeBeanBase();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "layer model;" + NL + "" + NL + "import java.util.List;" + NL + "import javax.servlet.*;" + NL + "import javax.persistence.*;" + NL + "" + NL + "public class ";
  protected final String TEXT_2 = "Facade implements ServletContextListener {" + NL + "" + NL + "    @PersistenceUnit(unitName = \"store-pu\")" + NL + "    private EntityManagerFactory emf;" + NL + "" + NL + "    private EntityManager em;" + NL + "" + NL + "    public CustomerRepository() {" + NL + "\t\temf = (EntityManagerFactory) Persistence.createEntityManagerFactory(\"store-pu\");" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public void contextDestroyed(ServletContextEvent sce) {" + NL + "\t\tif (emf.isOpen()) {" + NL + "\t   \t\temf.close();" + NL + "\t\t}" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public void contextInitialized(ServletContextEvent sce) {" + NL + "\t\tServletContext context = sce.getServletContext();" + NL + "\t\tcontext.setAttribute(\"customerRepository\", this);" + NL + "    }" + NL + "" + NL + "    public final EntityManager entityManager() {" + NL + "\t\tif (em == null || !em.isOpen()) {" + NL + "\t\t    em = emf.createEntityManager();" + NL + "\t\t}" + NL + "\t\treturn em;" + NL + "    }" + NL + "" + NL + "    public void persistOrMerge(Serializable entity, Serializable id) {" + NL + "\t\tem = entityManager();" + NL + "" + NL + "\t\tif (entity == null) {" + NL + "\t\t    throw new IllegalArgumentException(\"entity\");" + NL + "\t\t}" + NL + "\t\ttry {" + NL + "\t    \tem.getTransaction().begin();" + NL + "\t    \tif (id == null) {" + NL + "\t\t\t\tem.persist(entity);" + NL + "\t    \t} else {" + NL + "\t\t\t\tem.merge(entity);" + NL + "\t   \t\t}" + NL + "\t    \tem.getTransaction().commit();" + NL + "\t\t} finally {" + NL + "\t    \tem.close();" + NL + "\t\t}" + NL + "    }";
  protected final String TEXT_3 = NL + "\tprivate ";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = ";";
  protected final String TEXT_6 = NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_7 = "Facade(){ }" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_8 = "Facade(Object[] o){" + NL + "\t\t";
  protected final String TEXT_9 = "Constructor(o);" + NL + "\t}";
  protected final String TEXT_10 = NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_11 = "FacadeConstructor(Object[] o){}";
  protected final String TEXT_12 = "\t" + NL + "\t" + NL + "\tpublic void ";
  protected final String TEXT_13 = "FacadeConstructor(Object[] o){";
  protected final String TEXT_14 = NL + "\t\tthis.";
  protected final String TEXT_15 = " = (";
  protected final String TEXT_16 = ") o[";
  protected final String TEXT_17 = "];";
  protected final String TEXT_18 = "\t\t" + NL + "\t}";
  protected final String TEXT_19 = "\t" + NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_20 = " get";
  protected final String TEXT_21 = "(){ return this.";
  protected final String TEXT_22 = "; }" + NL + "\t" + NL + "\tpublic void set";
  protected final String TEXT_23 = "(";
  protected final String TEXT_24 = " ";
  protected final String TEXT_25 = "){" + NL + "\t\tthis.";
  protected final String TEXT_26 = " = ";
  protected final String TEXT_27 = ";" + NL + "\t}";
  protected final String TEXT_28 = "\t" + NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_29 = " ";
  protected final String TEXT_30 = "(";
  protected final String TEXT_31 = ", ";
  protected final String TEXT_32 = ")" + NL + "\t{" + NL + "\t\t// TODO" + NL + "\t}";
  protected final String TEXT_33 = NL + "}" + NL;
  protected final String TEXT_34 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Facade f = (Facade)((Object[])argument)[0];
	Vector<Attribute> atts = (Vector<Attribute>)((Object[])argument)[1];
	Vector<Operation> ops = (Vector<Operation>)((Object[])argument)[2];

    stringBuffer.append(TEXT_1);
    stringBuffer.append(f.getName() );
    stringBuffer.append(TEXT_2);
    
	for(Attribute a: atts){

    stringBuffer.append(TEXT_3);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_5);
    
	}

    stringBuffer.append(TEXT_6);
    stringBuffer.append(f.getName() );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(f.getName() );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(f.getName() );
    stringBuffer.append(TEXT_9);
    
	if(atts.size()==0){

    stringBuffer.append(TEXT_10);
    stringBuffer.append(f.getName() );
    stringBuffer.append(TEXT_11);
    
	}
	else {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(f.getName() );
    stringBuffer.append(TEXT_13);
    
	int i = 0;
	for(Attribute a: atts){

    stringBuffer.append(TEXT_14);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_15);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_16);
    stringBuffer.append(i );
    stringBuffer.append(TEXT_17);
    
		
		i++;
	}

    stringBuffer.append(TEXT_18);
    
	}
	for(Attribute a: atts){
		String attrName = (""+ a.getName().charAt(0)).toUpperCase() + a.getName().substring(1,a.getName().length());

    stringBuffer.append(TEXT_19);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_21);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(attrName );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(a.getType() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(a.getName() );
    stringBuffer.append(TEXT_27);
    
	}

	for(Operation o: ops){
		Vector<Parameter> parameters = o.getParameters();

    stringBuffer.append(TEXT_28);
    stringBuffer.append(o.getType() );
    stringBuffer.append(TEXT_29);
    stringBuffer.append(o.getName() );
    stringBuffer.append(TEXT_30);
     for(Parameter p: parameters){ 
    stringBuffer.append(p.getType() +" "+ p.getName() );
    if(p.getName() != ((Parameter) parameters.lastElement()).getName()){ 
    stringBuffer.append(TEXT_31);
    } }
    stringBuffer.append(TEXT_32);
    
	}

    stringBuffer.append(TEXT_33);
    stringBuffer.append(TEXT_34);
    return stringBuffer.toString();
  }
}

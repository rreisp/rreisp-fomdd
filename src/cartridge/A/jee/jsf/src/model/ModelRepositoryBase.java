package cartridge.A.jee.jsf.src.model;

import java.util.Vector;
import core.mvc.model.*;

public class ModelRepositoryBase
{
  protected static String nl;
  public static synchronized ModelRepositoryBase create(String lineSeparator)
  {
    nl = lineSeparator;
    ModelRepositoryBase result = new ModelRepositoryBase();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "layer model.repository;" + NL + "" + NL + "" + NL + "import java.io.Serializable;" + NL + "import java.util.List;" + NL + "" + NL + "import javax.persistence.EntityManager;" + NL + "import javax.persistence.EntityManagerFactory;" + NL + "import javax.persistence.Persistence;" + NL + "import javax.persistence.PersistenceUnit;" + NL + "import javax.persistence.Query;" + NL + "import javax.servlet.ServletContext;" + NL + "import javax.servlet.ServletContextEvent;" + NL + "import javax.servlet.ServletContextListener;" + NL + "import model.*;" + NL + "" + NL + "public class ";
  protected final String TEXT_2 = "Repository implements ServletContextListener {" + NL + "" + NL + "    @PersistenceUnit(unitName = \"store-pu\")" + NL + "    private EntityManagerFactory emf;" + NL + "" + NL + "    private EntityManager em;" + NL + "" + NL + "    public ";
  protected final String TEXT_3 = "Repository() {" + NL + "\t\temf = (EntityManagerFactory) Persistence.createEntityManagerFactory(\"store-pu\");" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public void contextDestroyed(ServletContextEvent sce) {" + NL + "\t\tif (emf.isOpen()) {" + NL + "\t   \t\temf.close();" + NL + "\t\t}" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public void contextInitialized(ServletContextEvent sce) {" + NL + "\t\tServletContext context = sce.getServletContext();" + NL + "\t\tcontext.setAttribute(\"";
  protected final String TEXT_4 = "Repository\", this);" + NL + "    }" + NL + "" + NL + "    public final EntityManager entityManager() {" + NL + "\t\tif (em == null || !em.isOpen()) {" + NL + "\t\t    em = emf.createEntityManager();" + NL + "\t\t}" + NL + "\t\treturn em;" + NL + "    }" + NL + "" + NL + "    public void persistOrMerge(Serializable entity, Serializable id) {" + NL + "\t\tem = entityManager();" + NL + "" + NL + "\t\tif (entity == null) {" + NL + "\t\t    throw new IllegalArgumentException(\"entity\");" + NL + "\t\t}" + NL + "\t\ttry {" + NL + "\t    \tem.getTransaction().begin();" + NL + "\t    \tif (id == null) {" + NL + "\t\t\t\tem.persist(entity);" + NL + "\t    \t} else {" + NL + "\t\t\t\tem.merge(entity);" + NL + "\t   \t\t}" + NL + "\t    \tem.getTransaction().commit();" + NL + "\t\t} finally {" + NL + "\t    \tem.close();" + NL + "\t\t}" + NL + "    }" + NL + "" + NL + "    public ";
  protected final String TEXT_5 = " find";
  protected final String TEXT_6 = "ById(Long id) {" + NL + "\t\tem = entityManager();" + NL + "\t\treturn em.find(";
  protected final String TEXT_7 = ".class, id);" + NL + "    }" + NL + "" + NL + "\tpublic List findAll";
  protected final String TEXT_8 = "s() {" + NL + "\t\tem = entityManager();" + NL + "\t\tQuery q = em.createQuery(\"select obj from ";
  protected final String TEXT_9 = " obj\");" + NL + "\t\treturn q.getResultList();" + NL + "\t}" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_10 = " new";
  protected final String TEXT_11 = "(";
  protected final String TEXT_12 = " List ";
  protected final String TEXT_13 = " ";
  protected final String TEXT_14 = ", ";
  protected final String TEXT_15 = ") {" + NL + "\t\t";
  protected final String TEXT_16 = " object = new ";
  protected final String TEXT_17 = "();" + NL + "\t\t" + NL + "\t\t// TODO: This part you'll need to write by hand. Sorry!" + NL + "\t\t// Set the attributes of object here" + NL + "\t\t// Insert one or more parameters if necessary" + NL + "\t\t" + NL + "\t\tpersistOrMerge(object, object.getId());" + NL + "\t\treturn object;" + NL + "\t}" + NL + "    " + NL + "}" + NL;
  protected final String TEXT_18 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Model m = (Model) ((Object[])argument)[0];
	Vector<Attribute> atts = (Vector<Attribute>)((Object[])argument)[1];


    stringBuffer.append(TEXT_1);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(m.getName().toLowerCase());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(m.getName() );
    stringBuffer.append(TEXT_11);
     for(Attribute a : atts) { if (a.getFeature().compareTo("") == 0) { if (a.getMetaType() != null && a.getMetaType().equals("OneToMany")) { 
    stringBuffer.append(TEXT_12);
    stringBuffer.append(a.getName());
     } else { 
    stringBuffer.append(a.getType());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(a.getName());
    	} if (a.getName() != ((Attribute) atts.lastElement()).getName()) { 
    stringBuffer.append(TEXT_14);
     } } } 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(m.getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}

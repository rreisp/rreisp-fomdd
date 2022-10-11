package cartridge.A.jee.jsf.configuration;

public class PomXML
{
  protected static String nl;
  public static synchronized PomXML create(String lineSeparator)
  {
    nl = lineSeparator;
    PomXML result = new PomXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version='1.0' encoding='UTF-8'?>";
  protected final String TEXT_2 = NL + "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" + NL + "\txsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">" + NL + "\t<modelVersion>4.0.0</modelVersion>" + NL + "\t" + NL + "\t<groupId>ufu.br.fomdd</groupId>" + NL + "\t<artifactId>";
  protected final String TEXT_3 = "</artifactId>" + NL + "\t<packaging>war</packaging>" + NL + "\t<version>1.0-SNAPSHOT</version>" + NL + "\t<name>";
  protected final String TEXT_4 = " Maven Webapp</name>" + NL + "\t<url>http://maven.apache.org</url>" + NL + "\t<dependencies>" + NL + "\t\t<dependency>" + NL + "\t\t\t<groupId>junit</groupId>" + NL + "\t\t\t<artifactId>junit</artifactId>" + NL + "\t\t\t<version>3.8.1</version>" + NL + "\t\t\t<scope>test</scope>" + NL + "\t\t</dependency>" + NL + "\t\t<dependency>" + NL + "\t\t\t<groupId>javax.persistence</groupId>" + NL + "\t\t\t<artifactId>persistence-api</artifactId>" + NL + "\t\t\t<version>1.0</version>" + NL + "\t\t\t<scope>provided</scope>" + NL + "\t\t</dependency>" + NL + "\t\t<dependency>" + NL + "\t\t\t<groupId>org.hibernate</groupId>" + NL + "\t\t\t<artifactId>hibernate-entitymanager</artifactId>" + NL + "\t\t\t<version>3.2.1.ga</version>" + NL + "\t\t\t<scope>provided</scope>" + NL + "\t\t</dependency>" + NL + "\t\t<dependency>" + NL + "\t\t\t<groupId>javax.servlet</groupId>" + NL + "\t\t\t<artifactId>servlet-api</artifactId>" + NL + "\t\t\t<version>2.4</version>" + NL + "\t\t</dependency>" + NL + "\t\t<dependency>" + NL + "\t\t\t<groupId>javax.servlet.jsp</groupId>" + NL + "\t\t\t<artifactId>jsp-api</artifactId>" + NL + "\t\t\t<version>2.0</version>" + NL + "\t\t</dependency>" + NL + "\t</dependencies>" + NL + "\t<build>" + NL + "\t\t<plugins>" + NL + "\t\t\t<plugin>" + NL + "\t\t\t\t<artifactId>maven-compiler-plugin</artifactId>" + NL + "\t\t\t\t<configuration>" + NL + "\t\t\t\t\t<source>1.6</source>" + NL + "\t\t\t\t\t<target>1.6</target>" + NL + "\t\t\t\t</configuration>" + NL + "\t\t\t</plugin>" + NL + "\t\t\t<plugin>" + NL + "\t\t\t\t<groupId>org.apache.maven.plugins</groupId>" + NL + "\t\t\t\t<artifactId>maven-eclipse-plugin</artifactId>" + NL + "\t\t\t\t<configuration>" + NL + "\t\t\t\t\t<wtpversion>1.5</wtpversion>" + NL + "\t\t\t\t</configuration>" + NL + "\t\t\t</plugin>" + NL + "\t\t</plugins>" + NL + "\t\t<finalName>";
  protected final String TEXT_5 = "</finalName>" + NL + "\t</build>" + NL + "</project>";
  protected final String TEXT_6 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	String productName = (String) ((Object[])argument)[0];

    stringBuffer.append(TEXT_2);
    stringBuffer.append( productName );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( productName );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( productName );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}

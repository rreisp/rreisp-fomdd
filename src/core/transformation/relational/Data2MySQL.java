package core.transformation.relational;

import java.io.File;
import org.w3c.dom.*;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class Data2MySQL {
	private DOMParser parser;
	private Document document;
	
	public Data2MySQL(){
		this.parser = new DOMParser();
	}

	public String translate(File input){
		String query = "";
		try {
			this.parser = new DOMParser();
			parser.parse(input.getPath());
			this.document = parser.getDocument();

			NodeList nl = document.getElementsByTagName("table");

			for(int i=0;i<nl.getLength();i++){
				Node n = nl.item(i);
				query += "DROP TABLE IF EXISTS "+ n.getNodeName() +";\n";
				query += "CREATE TABLE "+ n.getNodeName() +"(\n";
				
				NodeList children = n.getChildNodes();
				
				for(int j=0;j<children.getLength();j++){
					Node child = children.item(j);
					if(child.getNodeName().compareTo("attribute")==0){
						NamedNodeMap map = child.getAttributes();
						String name = map.getNamedItem("name").getNodeValue();
						String type = map.getNamedItem("type").getNodeValue();
						if(type.compareTo("Integer")==0){
							type = "int";
						}
						else if(type.compareTo("")==0){
							
						}
						query += "\t"+ name +" "+ type + ";\n";
					}
				}
				query += ");\n\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(stateMachine.getName());
		return query;
	}
}

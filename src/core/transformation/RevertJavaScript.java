package core.transformation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class RevertJavaScript {
	private DOMParser parser;
	private Document document;
	
	public RevertJavaScript()  {
		super();
		this.parser = new DOMParser();		
	}
	
	public String translate(File input){
		String s = "";
		try {
			this.parser = new DOMParser();
			parser.parse(input.getPath());
			this.document = parser.getDocument();
			NodeList functions = document.getElementsByTagName("function");

			for(int i=0;i<functions.getLength();i++){
				NamedNodeMap nm = functions.item(i).getAttributes();
				for(int j=0;j<nm.getLength();j++){					
					s += functions.item(i).getNodeName() +" "+ nm.getNamedItem("name").getNodeValue() + "{"+
					functions.item(i).getTextContent()+"}\n\n";
				}
			}			
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}	

	public static void main(String args[]){
		File dir = new File(args[0]);
		
		class SimpleFileFilter implements FileFilter
		{
		  public boolean accept(File file)
		  {
		      if (file.getName().toLowerCase().endsWith(".js")) return true;
		      else return false;
		  }
		}
		
		File[] fs = dir.listFiles();
		SimpleFileFilter sff = new SimpleFileFilter();
		RevertJavaScript rv = new RevertJavaScript();
		
		for(int i=0;i<fs.length;i++){
			if(sff.accept(fs[i])){
				try {
		        	String text = rv.translate(fs[i]);
					FileWriter fstream = new FileWriter(fs[i]);
					BufferedWriter out = new BufferedWriter(fstream);
		        	out.write(text);
		        	out.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}

	}
}


package core.model.input;

import java.io.File;
import java.io.FileReader;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import core.model.FeatureModel;
import core.model.MVCControl;
import core.mvc.controller.*;

public class XML2ObjectMVCControllerTranslator extends DefaultHandler implements XML2ObjectInput {
	private XMLReader xr;
	private MVCControl controllers;
	private int screenPointer;
	private int handlerPointer;
	
	public XML2ObjectMVCControllerTranslator(){
		super();

		this.screenPointer = -1;
		this.handlerPointer = -1;
		this.controllers = new MVCControl();
	}

	public MVCControl translate(File input){
		try {
			this.xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(this);
			xr.setErrorHandler(this);			
			FileReader r = new FileReader(input);
			xr.parse(new InputSource(r));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return controllers;
	}

	public MVCControl getControllers() {
		return controllers;
	}

	public void setControllers(MVCControl controllers) {
		this.controllers = controllers;
	}

	public void startElement (String uri, String name,
			String qName, Attributes atts)
	{
		if(name.compareTo("screen") == 0){
			this.screenPointer++;
			this.handlerPointer = -1;
			controllers.addScreen(new Screen(atts.getValue("name"),atts.getValue("feature")));
		}
		else if(name.compareTo("handler") == 0){
			this.handlerPointer++;
			this.controllers.getScreens().elementAt(screenPointer).addHandler(new Handler(atts.getValue("event"),atts.getValue("feature"),atts.getValue("name"),atts.getValue("source"),atts.getValue("target")));
		}
	}
	
	public void sortByFeatures(FeatureModel fm){
		MVCControl control = new MVCControl();
		for(Screen s:this.getControllers().getScreens()){
			Screen sAux = new Screen(s.getName(),s.getFeature());
			for(Handler h:s.getHandlers()){
				if(h.getFeature().compareTo("")==0) sAux.addHandler(h);
			}
			
			for(String feature : fm.getFeatures()){
				if(s.count(feature) != 0){
					for(Handler h:s.getHandlers()){
						if(h.getFeature().compareTo(feature)==0){
							sAux.addHandler(h);
						}
					}	
				}				
			}
			control.addScreen(sAux);
		}
		this.controllers = control;
	}

	public void print(){
		this.controllers.print();
	}
}

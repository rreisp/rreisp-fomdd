package core.model.input;

import java.io.File;
import java.io.FileReader;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import core.model.FeatureModel;
import core.model.MVCModel;
import core.mvc.model.*;

public class XML2ObjectMVCModelTranslator extends DefaultHandler implements XML2ObjectInput {
	private XMLReader xr;
	private MVCModel modelEntities;
	private int modelPointer;
	private int facadePointer;
	private int attributePointer;
	private int operationPointer;
	private int parameterPointer;

	public XML2ObjectMVCModelTranslator() {
		super();

		this.modelPointer = -1;
		this.facadePointer = -1;
		this.attributePointer = -1;
		this.operationPointer = -1;
		this.parameterPointer = -1;
		this.modelEntities = new MVCModel();
	}

	public MVCModel translate(File input) {
		try {
			this.xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(this);
			xr.setErrorHandler(this);
			FileReader r = new FileReader(input);
			xr.parse(new InputSource(r));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelEntities;
	}

	public MVCModel getModelEntities() {
		return modelEntities;
	}

	public void setModelEntities(MVCModel modelEntities) {
		this.modelEntities = modelEntities;
	}

	public void startElement(String uri, String name, String qName, Attributes atts) {
		if (name.compareTo("model") == 0) {
			this.modelPointer++;
			this.facadePointer = -1;
			this.attributePointer = -1;
			this.operationPointer = -1;
			this.parameterPointer = -1;
			modelEntities.addModel(new Model(atts.getValue("feature"), atts.getValue("name")));
		}
		else if (name.compareTo("facade") == 0) {
			this.facadePointer++;
			this.modelPointer = -1;
			this.attributePointer = -1;
			this.operationPointer = -1;
			this.parameterPointer = -1;
			modelEntities.addFacade(new Facade(atts.getValue("feature"), atts.getValue("name")));
		}
		else if (name.compareTo("attribute") == 0) {
			this.attributePointer++;
			this.parameterPointer = 0;
			int pointer = -1;
			if (modelPointer == -1) {
				pointer = facadePointer;
				this.modelEntities.getFacades().elementAt(pointer).addAttribute(
						new Attribute(atts.getValue("feature"), atts.getValue("name"), atts.getValue("type"), atts
								.getValue("metaType"), atts.getValue("direction")));
			}
			else {
				pointer = modelPointer;
				this.modelEntities.getModels().elementAt(pointer).addAttribute(
						new Attribute(atts.getValue("feature"), atts.getValue("name"), atts.getValue("type"), atts
								.getValue("metaType"), atts.getValue("direction")));
			}
		}
		else if (name.compareTo("operation") == 0) {
			this.operationPointer++;
			this.parameterPointer = 0;
			int pointer = -1;
			if (modelPointer == -1) {
				pointer = facadePointer;
				this.modelEntities.getFacades().elementAt(pointer).addOperation(
						new Operation(atts.getValue("feature"), atts.getValue("name"), atts.getValue("type")));
			}
			else {
				pointer = modelPointer;
				this.modelEntities.getModels().elementAt(pointer).addOperation(
						new Operation(atts.getValue("feature"), atts.getValue("name"), atts.getValue("type")));
			}
		}
		else if (name.compareTo("parameter") == 0) {
			this.parameterPointer++;
			int pointer = -1;
			if (modelPointer == -1) {
				pointer = facadePointer;
				this.modelEntities.getFacades().elementAt(pointer).getOperations().elementAt(operationPointer)
						.addParameter(new Parameter(atts.getValue("name"), atts.getValue("type")));
			}
			else {
				pointer = modelPointer;
				this.modelEntities.getModels().elementAt(pointer).getOperations().elementAt(operationPointer)
						.addParameter(new Parameter(atts.getValue("name"), atts.getValue("type")));
			}
		}
	}

	public void sortByFeatures(FeatureModel fm) {
		for (Model m : this.getModelEntities().getModels()) {
			// TODO
		}
	}

	public void print() {
		this.modelEntities.print();
	}
}

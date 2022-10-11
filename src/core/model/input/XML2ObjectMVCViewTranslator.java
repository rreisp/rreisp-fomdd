package core.model.input;

import java.io.File;
import java.io.FileReader;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import core.model.FeatureModel;
import core.model.MVCView;
import core.mvc.view.Element;
import core.mvc.view.Screen;
import core.mvc.view.Transition;

public class XML2ObjectMVCViewTranslator extends DefaultHandler implements XML2ObjectInput {
	private XMLReader xr;
	private MVCView views;
	private Stack xmlElements;
	private Stack xmlNameElements;
	private Screen currentScreen;
	private Element currentElement;
	private Transition currentTransition;

	@SuppressWarnings("unchecked")
	public XML2ObjectMVCViewTranslator() {
		super();
		this.views = new MVCView();
		this.xmlElements = new Stack();
		this.xmlNameElements = new Stack();
	}

	public MVCView translate(File input) {
		try {
			this.xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(this);
			xr.setErrorHandler(this);
			FileReader r = new FileReader(input);
			xr.parse(new InputSource(r));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return views;
	}

	public MVCView getViews() {
		return views;
	}

	public void setViews(MVCView views) {
		this.views = views;
	}

	@SuppressWarnings("unchecked")
	public void startElement(String uri, String name, String qName, Attributes atts) {
		if (name.compareTo("screen") == 0) {
			currentScreen = new Screen(atts.getValue("name"), atts.getValue("feature"), atts.getValue("layout"));
			views.addScreen(currentScreen);
			xmlElements.push(currentScreen);

		}
		else
			if (name.compareTo("element") == 0) {
				currentElement = new Element(atts.getValue("feature"), atts.getValue("name"), atts.getValue("type"),
						atts.getValue("at"), Integer.parseInt(atts.getValue("depth")));

				if (((String) xmlNameElements.peek()).compareTo("element") == 0) {
					((Element) xmlElements.peek()).addElement(currentElement);
				}
				else
					if (((String) xmlNameElements.peek()).compareTo("screen") == 0) {
						((Screen) xmlElements.peek()).addElement(currentElement);
					}
				xmlElements.push(currentElement);

			}
			else
				if (name.compareTo("transition") == 0) {
					currentTransition = new Transition(atts.getValue("name"), atts.getValue("source"), atts
							.getValue("target"));
					((Element) xmlElements.peek()).addTransition(currentTransition);
					xmlElements.push(currentTransition);
				}
		xmlNameElements.push(name);
	}

	public void endElement(String uri, String localName, String qName) {
		if (!xmlElements.empty()) {
			xmlElements.pop();
			xmlNameElements.pop();
		}
	}

	public void sortByFeatures(FeatureModel featureModel) {
		MVCView views = new MVCView();
		for (Screen screen : this.getViews().getScreens()) {
			Screen sAux = new Screen(screen.getName(), screen.getFeature(), screen.getLayout());
			for (Element e : screen.getElements()) {
				if (e.getFeature().compareTo("") == 0)
					sAux.addElement(e);
			}
			for (String feature : featureModel.getFeatures()) {
				if (screen.count(feature) != 0) {
					for (Element e : screen.getElements()) {
						if (e.getFeature().compareTo(feature) == 0) {
							sAux.addElement(e);
						}
					}
				}
			}
			views.addScreen(sAux);
		}
		this.views = views;
	}

	public void print() {
		this.views.print();
	}
}

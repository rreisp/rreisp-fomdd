package core.refinement;

import java.util.HashMap;
import java.util.Vector;

import core.mvc.view.Element;
import core.mvc.view.Screen;

public class ViewRefinementDocument {
	private Screen screen;
	private Vector<String> features;
	private HashMap<String, Vector<Element>> featureElementsMap;

	public ViewRefinementDocument(Screen screen) {
		this.screen = screen;
		this.featureElementsMap = new HashMap<String, Vector<Element>>();
		this.features = new Vector<String>();
		
		generateFeatureList();
	}

	private void generateFeatureList() {
		Vector<Element> elements = getAllElements();
		String feature;
		
		features.add(screen.getFeature());
		featureElementsMap.put(screen.getFeature(), new Vector<Element>());
		
		for (Element element : elements) {
			feature = element.getFeature();
			if (!features.contains(feature)) {
				features.add(feature);
			}
			
			if (!featureElementsMap.containsKey(feature)) {
				featureElementsMap.put(feature, new Vector<Element>());
			}
			Vector<Element> featureElements = featureElementsMap.get(feature);
			featureElements.add(element);
			featureElementsMap.put(feature, featureElements);
		}
	}

	public Vector<Element> getAllElementsByFeature(String feature) {
		return featureElementsMap.get(feature);
	}

	public Vector<Element> getAllElements() {
		Vector<Element> elementsList = screen.getElements();
		Vector<Element> elements = new Vector<Element>();
		
		for (Element e : elementsList) {
			elements.addAll(getAllChildElements(e));
		}
		return elements;
	}

	private Vector<Element> getAllChildElements(Element e) {
		Vector<Element> elements = new Vector<Element>();
		Vector<Element> childElements = e.getElements();

		elements.add(e);

		for (Element child : childElements) {
			elements.addAll(getAllChildElements(child));
		}
		return elements;
	}

	/*
	 * TODO: Revisar a utilizacao do metodo
	 */
	public Vector<String> getAllElementTypes() {
		Vector<String> elementsTypes = new Vector<String>();
		for (Element e : this.screen.getElements()) {
			expand(e, elementsTypes);
		}
		return elementsTypes;
	}

	private void expand(Element e, Vector<String> elements) {
		if (!elements.contains(e.getType())) {
			elements.add(e.getType());
		}
		for (Element child : e.getElements()) {
			expand(child, elements);
		}
	}

	
	public Vector<String> getFeatures() {
		return features;
	}

	public void setFeatures(Vector<String> features) {
		this.features = features;
	}
	
	public void print() {
		System.out.println(this.screen.getName());
		for (String feature : this.features) {
			System.out.println("\t" + feature);

			for (Element e : this.featureElementsMap.get(feature)) {
				System.out.println("\t\t" + e.getName());
			}
		}
	}
}

package core.mvc.view;

import java.util.Vector;

public class Screen {
	private String name;
	private String feature;
	private String layout;

	private Vector<Element> elements;

	public Screen(String name, String feature, String layout) {
		this.name = name;
		this.feature = feature;
		this.layout = layout;
		this.elements = new Vector<Element>();
	}

	public boolean isBaseFeature() {
		return feature.isEmpty();
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Vector<Element> getElements() {
		return elements;
	}

	public void setElements(Vector<Element> elements) {
		this.elements = elements;
	}

	public void addElement(Element e) {
		this.elements.add(e);
	}

	public int count(String feature) {
		int counter = 0;
		for (Element e : this.getElements()) {
			if (e.getFeature().compareTo(feature) == 0)
				counter++;
		}
		return counter;
	}

	public void print() {
		System.out.println("\tScreen - Name: " + name);

		for (Element e : elements) {
			e.print();
		}
	}

	public Vector<String> getAllStereotypes() {
		Vector<String> aux = new Vector<String>();

		for (Element e : this.elements) {
			getElementStereotypes(e, aux);
		}

		return aux;
	}

	@Override
	public String toString() {
		return "Screen: " + name + " (" + feature + ")";
	}

	private void getElementStereotypes(Element e, Vector<String> stereotypes) {
		if (!stereotypes.contains(e.getType())) {
			stereotypes.add(e.getType());
		}
		for (Element child : e.getElements()) {
			getElementStereotypes(child, stereotypes);
		}
	}
}

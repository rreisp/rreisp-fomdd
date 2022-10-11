package core.mvc.view;

import java.util.Vector;

import core.fieldtype.ElementTranslator;

public class Element {
	private String feature;
	private String name;
	private String type;
	private String at;
	private int depth;
	private Vector<Transition> transitions;
	private Vector<Element> elements;

	public Element(String feature, String name, String type, String at, int depth) {
		this.feature = feature;
		this.name = name;
		this.type = type;
		this.at = at;
		this.depth = depth;
		this.transitions = new Vector<Transition>();
		this.elements = new Vector<Element>();
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}

	public Vector<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(Vector<Transition> transitions) {
		this.transitions = transitions;
	}

	public void addTransition(Transition t) {
		this.transitions.add(t);
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

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String generate(ElementTranslator et) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(et.translateBegin(this));

//		for (Element e : getElements()) {
//			buffer.append(e.generate(et));
//		}

		buffer.append(et.translateEnd(this));
		return buffer.toString();
	}

	@Override
	public String toString() {
		return "Element: " + name + " (feature: " + feature + "/at: " + at + ")";
	}

	public void print() {
		System.out.println("\t\tElement - Name: " + name + "\tFeature: " + feature);

		for (Transition t : transitions) {
			t.print();
		}

		for (Element e : elements) {
			e.print();
		}
	}
}

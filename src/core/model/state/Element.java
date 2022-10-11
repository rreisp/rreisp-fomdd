package core.model.state;

import java.util.Vector;

public class Element {
	private Element parent;
	private String name;
	private String type;
	private int depth;
	private Vector<Element> elements;
	private Vector<Transition> transitions;
	
	public Element(String name, String type, int depth){
		this.parent = null;
		this.name = name;
		this.type = type;
		this.depth = depth;
		this.elements = new Vector<Element>();
		this.transitions = new Vector<Transition>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Element getParent() {
		return parent;
	}

	public void setParent(Element parent) {
		this.parent = parent;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Vector<Element> getElements() {
		return elements;
	}

	public void setElements(Vector<Element> elements) {
		this.elements = elements;
	}

	public Vector<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(Vector<Transition> transitions) {
		this.transitions = transitions;
	}
	
	public void addTransition(Transition t){
		this.transitions.add(t);
	}
	
	public void addElement(Element e){
		this.elements.add(e);
	}

	public void print(){
		String tab = "\t\t\t";
		
		for(int i=0;i<this.depth;i++){
			tab += "\t";
		}
		System.out.println(tab + this.name + " "+ this.depth);
		for(Element e:this.elements){
			e.print();
		}
	}	
}

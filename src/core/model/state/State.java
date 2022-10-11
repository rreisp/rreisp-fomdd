package core.model.state;

import java.util.Vector;

public class State {
	private String name;
	private Vector<Element> elements;
	
	public State(String name){
		this.name = name;
		this.elements = new Vector<Element>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<Element> getElements() {
		return elements;
	}

	public void setElements(Vector<Element> elements) {
		this.elements = elements;
	}
	
	public void addElement(Element e){
		this.elements.add(e);
	}
	
	public void print(){
		System.out.println("\t\t"+this.name);
		for(Element e:this.elements){
			e.print();
		}
	}		
	
}

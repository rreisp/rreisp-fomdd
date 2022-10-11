package core.mvc.model;

import java.util.Vector;

public class Facade {
	private String feature;
	private String name;
	private Vector<Attribute> attributes;
	private Vector<Operation> operations;
	
	public Facade(String feature, String name){
		this.feature = feature;
		this.name = name;
		this.attributes = new Vector<Attribute>();
		this.operations = new Vector<Operation>();
	}

	public boolean isBaseFeature() {
		return feature.isEmpty();
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

	public void print(){
		System.out.println("\tFacade - Name: "+ name + "\tFeature: "+ feature);
		
		for(Attribute a : attributes){
			a.print();
		}
		for(Operation o : operations){
			o.print();
		}			
	}

	public Vector<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Vector<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Vector<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Vector<Operation> operations) {
		this.operations = operations;
	}
	
	public void addAttribute(Attribute attribute){
		this.attributes.add(attribute);
	}	
	
	public void addOperation(Operation operation){
		this.operations.add(operation);
	}
}

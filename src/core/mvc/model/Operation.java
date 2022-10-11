package core.mvc.model;

import java.util.Vector;

import core.fieldtype.Visitor;

public class Operation {
	private String feature;
	private String name;
	private String type;
	private Vector<Parameter> parameters;
	
	public Operation(String feature, String name, String type){
		this.feature = feature;
		this.name = name;
		this.type = type;
		this.parameters = new Vector<Parameter>();
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

	public Vector<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(Vector<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(Parameter p){
		this.parameters.add(p);
	}
	
	public void print(){
		System.out.println("\t\tOperation - Name: "+ name + "\tType: "+ type + "\tFeature: "+ feature);
		for(Parameter p : parameters){
			p.print();
		}			
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
}

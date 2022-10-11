package core.mvc.model;

import core.fieldtype.Visitor;

public class Parameter {
	private String name;
	private String type;
	
	public Parameter(String name, String type){
		this.name = name;
		this.type = type;
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

	public void print(){
		System.out.println("\t\t\tParameter - Name: "+ name + "\tType: "+ type);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}	
}

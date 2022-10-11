package core.mvc.model;

import core.fieldtype.Visitor;

public class Attribute {

	private String feature;
	private String name;
	private String type;
	private String metaType;
	private String direction;
	
	public Attribute(String feature, String name, String type, String metaType, String direction){
		this.feature = feature;
		this.name = name;
		this.type = type;
		this.metaType = metaType;
		this.direction = direction;
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
	
	public void print(){
		System.out.println("\t\tAttribute - Name: "+ name + "\tType: "+ type + "\tFeature: "+ feature + "\tMetatype: " + metaType + "\tDirection: " + direction);
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}

	public String getMetaType() {
		return metaType;
	}

	public void setMetaType(String metaType) {
		this.metaType = metaType;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}
}

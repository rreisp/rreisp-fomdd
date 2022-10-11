package core.mvc.controller;

import java.util.Vector;

public class Screen {
	private String name;
	private String feature;
	
	private Vector<Handler> handlers;
	
	public Screen(String name, String feature){
		this.name = name;
		this.feature = feature;
		this.handlers = new Vector<Handler>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<Handler> getHandlers() {
		return handlers;
	}

	public void setHandlers(Vector<Handler> handlers) {
		this.handlers = handlers;
	}
	
	public void addHandler(Handler h){
		this.handlers.add(h);
	}
	
	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}	
	
	public int count(String feature){
		int counter = 0;
		for(Handler h:this.getHandlers()){
			if(h.getFeature().compareTo(feature)==0) counter++;
		}
		return counter;
	}
	
	@Override
	public String toString() {
		return "Screen: "+ name + " (" + feature + ")";
	}
	
	public void print(){
		System.out.println("\tScreen - Name: "+ name);
		
		for(Handler h:handlers){
			h.print();
		}
	}
}

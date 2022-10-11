package core.refinement;

import java.util.*;
import core.mvc.controller.*;

public class ControllerRefinementDocument {
	private String screenName;
	private Vector<String> features;
	private HashMap<String, Vector<Handler>> refinements;

	public ControllerRefinementDocument(Screen s){
		this.refinements = new HashMap<String, Vector<Handler>>();
		this.features = new Vector<String>();
		this.screenName = s.getName();
		
		for(Handler h:s.getHandlers()){
			if(! this.features.contains(h.getFeature())){
				this.features.add(h.getFeature());
			}
			
			Vector<Handler> handlers = refinements.get(h.getFeature());
			if(handlers == null){
				handlers = new Vector<Handler>();
			}
			handlers.add(h);
			refinements.put(h.getFeature(),handlers);
		}
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Vector<String> getFeatures() {
		return features;
	}

	public void setFeatures(Vector<String> features) {
		this.features = features;
	}	
	
	public HashMap<String, Vector<Handler>> getRefinements() {
		return refinements;
	}

	public void setRefinements(HashMap<String, Vector<Handler>> refinements) {
		this.refinements = refinements;
	}
	
	public Vector<Handler> getHandlersByFeature(String feature){
		Vector<Handler> hdlsAux = this.getRefinements().get(feature); 
		if(hdlsAux == null){
			hdlsAux = new Vector<Handler>();
		}
		return hdlsAux;
	}	
	
	public Vector<Handler> getHandlers(){
		Vector<Handler> hdlsAux = new Vector<Handler>();
		for(String feature:refinements.keySet()){
			Vector<Handler> hds = refinements.get(feature);
			for(Handler h:hds){
				hdlsAux.add(h);
			}
		}
		return hdlsAux;
	}
	
	public void print(){
		System.out.println(this.getScreenName());
		for(String feature : this.features){
			System.out.println("\t"+ feature);

			for(Handler e:this.refinements.get(feature)){
				System.out.println("\t\t"+ e.getName());
			}
		}
	}
}

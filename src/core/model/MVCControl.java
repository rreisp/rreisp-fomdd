package core.model;

import java.util.Vector;
import core.mvc.controller.*;

public class MVCControl {
	private Vector<Screen> screens;
	
	public MVCControl(){
		this.screens = new Vector<Screen>();
	}	
	
	public Vector<Screen> getScreens() {
		return screens;
	}

	public void setScreens(Vector<Screen> screens) {
		this.screens = screens ;
	}
	
	public void addScreen(Screen s){
		this.screens.add(s);
	}
	
	public Vector<Screen> selectScreensWith(String feature){
		Vector<Screen> screensWithTheFeature = new Vector<Screen>();
		
		for(Screen s:this.screens){
			if(s.getFeature().compareTo(feature)==0){
				screensWithTheFeature.add(s);
			}
			else {
				for(Handler h:s.getHandlers()){
					if(h.getFeature().compareTo(feature)==0){
						screensWithTheFeature.add(s);
						break;
					}
				}
			}
		}		
		return screensWithTheFeature;
	}
	
	public Vector<String> getFeatures(){
		Vector<String> features = new Vector<String>();
		
		for(Screen s:this.screens){
			if(! features.contains(s.getFeature()) && s.getFeature().compareTo("") !=0){
				features.add(s.getFeature());
			}
			
			for(Handler h:s.getHandlers()){
				if(! features.contains(h.getFeature()) && h.getFeature().compareTo("") !=0){
					features.add(h.getFeature());
				}
			}
		}
		
		return features;
	}
	
	public void print(){
		System.out.println(":: Abstract MVC Controller ::");
		
		for(Screen s : screens){
			s.print();
		}
	}
}

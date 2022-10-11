package core.model;

import java.util.Vector;
import core.mvc.view.Screen;

public class MVCView {
	private Vector<Screen> screens;
	
	public MVCView(){
		this.screens = new Vector<Screen>();
	}	
	
	public Vector<Screen> getScreens() {
		return screens;
	}

	public void setScreens(Vector<Screen> screens) {
		this.screens = screens;
	}
	
	public void addScreen(Screen s){
		this.screens.add(s);
	}	

	public void print(){
		System.out.println(":: Abstract MVC View ::");
		
		for(Screen s : screens){
			s.print();
		}
	}
}

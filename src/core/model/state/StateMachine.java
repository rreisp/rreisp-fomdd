package core.model.state;

import java.util.Vector;

public class StateMachine {
	private String name;
	private Vector<Screen> screens;
	
	public StateMachine(String name){
		this.name = name;
		this.screens = new Vector<Screen>();
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
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
		System.out.println(this.name);
		for(Screen s:this.screens){
			s.print();
		}
	}
}


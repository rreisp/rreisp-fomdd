package core.model.state;

import java.util.Vector;

public class Screen {
	private String name;
	private Vector<State> states;
	
	public Screen(String name){
		this.name = name;
		this.states = new Vector<State>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<State> getStates() {
		return states;
	}

	public void setStates(Vector<State> states) {
		this.states = states;
	}
	
	public void addState(State s){
		states.add(s);
	}
	
	public void print(){
		System.out.println("\t"+this.name);
		for(State s:this.states){
			s.print();
		}
	}	
}

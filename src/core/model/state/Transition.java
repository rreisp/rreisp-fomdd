package core.model.state;

public class Transition {
	private String name;
	private String source;
	private String target;
	
	public Transition(String name, String source, String target){
		this.name = name;
		this.source = source;
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public void print(){
		System.out.println(this.name +" "+ this.source +" "+ this.target);
	}
	
}

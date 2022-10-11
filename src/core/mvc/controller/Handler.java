package core.mvc.controller;

public class Handler {
	private String event;
	private String feature;
	private String name;
	private String source;
	private String target;
	
	public Handler(String event, String feature, String name, String source, String target){
		this.event = event;
		this.feature = feature;
		this.name = name;
		this.source = source;
		this.target = target;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
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
	
	@Override
	public String toString() {
		return "Handler: "+ name + " (" + feature + ")";
	}
	
	public void print(){
		System.out.println("\t\tHandler - Name: "+ name + "\tEvent: "+ event + "\tFeature: "+ feature + "\tSource: "+ source + "\tTarget: "+ target);
	}
}

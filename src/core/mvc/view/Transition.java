package core.mvc.view;

public class Transition {
	private String event;
	private String guard;
	private String action;
	private String name;
	private String source;
	private String target;

	public Transition(String name, String source, String target) {
		this.event = extractEvent(name);
		this.guard = extractGuard(name);
		this.action = extractAction(name);
		this.name = name;
		this.source = source;
		this.target = target;
	}

	private String extractEvent(String name) {
		String s = "";
		if (name.contains("/")) {
			String eventAndGuard = name.substring(0, name.indexOf("/"));
			if (eventAndGuard.contains("[")) {
				s = eventAndGuard.substring(0, eventAndGuard.indexOf("["));
			} else
				s = eventAndGuard;
		} else
			s = name;
		return s;
	}

	private String extractGuard(String name) {
		String s = "";
		if (name.contains("/")) {
			String eventAndGuard = name.substring(0, name.indexOf("/"));
			if (eventAndGuard.contains("[")) {
				String guard = eventAndGuard.substring(eventAndGuard.indexOf("["), eventAndGuard.indexOf("]"));
				s = guard;
			}
		}
		return s;
	}

	private String extractAction(String name) {
		String s = "";
		if (name.contains("/")) {
			String action = name.substring(name.indexOf("/") + 1, name.length());
			s = action;
		}
		return s;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getGuard() {
		return guard;
	}

	public void setGuard(String guard) {
		this.guard = guard;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public void print() {
		System.out.println("\t\t\tTransition - Name: " + this.name + "\tEvent: " + this.event + "\tGuard: " + this.guard + "\tAction: "
				+ this.action + "\tSource: " + this.source + "\tTarget: " + this.target);
	}

	@Override
	public String toString() {
		return "Transition [action=" + action + ", event=" + event + ", guard=" + guard + ", name=" + name + ", source=" + source
				+ ", target=" + target + "]";
	}

}

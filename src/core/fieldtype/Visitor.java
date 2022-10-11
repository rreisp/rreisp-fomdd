package core.fieldtype;

import core.mvc.model.*;

public interface Visitor {
	public void visit(Attribute att);
	public void visit(Operation op);
	public void visit(Parameter p);
}

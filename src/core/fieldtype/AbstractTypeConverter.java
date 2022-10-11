package core.fieldtype;

import core.mvc.model.*;

public abstract class AbstractTypeConverter implements Visitor {
	@Override
	public abstract void visit(Attribute att);
	@Override
	public abstract void visit(Operation op);
	@Override
	public abstract void visit(Parameter p);
}

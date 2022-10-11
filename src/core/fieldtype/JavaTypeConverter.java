package core.fieldtype;

import core.mvc.model.*;

public class JavaTypeConverter extends AbstractTypeConverter {

	@Override
	public void visit(Attribute att) {
		if(att.getType().contains("collection")){
			att.setType("List");
		}
	}

	@Override
	public void visit(Operation op) {
		if(op.getType().contains("collection")){
			op.setType("List");
		}		
	}

	@Override
	public void visit(Parameter p) {
		if(p.getType().contains("collection")){
			p.setType("List");
		}		
	}
}

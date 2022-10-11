package core.fieldtype;

import java.util.*;
import core.mvc.view.Element;

public abstract class ElementTranslator {
	private Stack<String> listStack = new Stack<String>();
	
	public abstract String translateBegin(Element e);
	public abstract String translateEnd(Element e);
	
	public Stack<String> getListStack() {
		return listStack;
	}
	public void setListStack(Stack<String> listStack) {
		this.listStack = listStack;
	}
}

package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerAction {
	void execute(HttpServletRequest request, HttpServletResponse response);
}

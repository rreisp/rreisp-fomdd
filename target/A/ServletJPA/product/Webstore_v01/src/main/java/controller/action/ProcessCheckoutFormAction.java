package controller.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.repository.*;
import model.action.*;
import model.*;

public class ProcessCheckoutFormAction implements ControllerAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServletContext context = request.getSession().getServletContext();
		try {
   
			// TODO This part you'll need to write by hand. Sorry!
			// Do all processing steps (save, update)  and forward to another GoToSomewhere action (through 'execute' method)
	
		} catch (Exception e) {
	 	   e.printStackTrace();
		}
	
	}
}

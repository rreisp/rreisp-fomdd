
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.action.*;

public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ACTION_IDENTIFIER = "action";
	private Map actions = new HashMap();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	public final void init$$controller() {
		// TODO This part you'll need to write by hand. Sorry!
		// Action classes must be coded by hand and implement the "controller.ControllerAction" interface.
		// Follow the pattern below to include all your custom actions:
		// actions.put("goToHome", new GoToHome());
		actions.put("goToHome", new GoToHomeAction());
		 	 	  
	}

	public final void init$$controllerBasicFrontEndDefinitions() {
		init$$controller();
		actions.put("goToCatalog", new GoToCatalogAction());
		actions.put("verifyCatalogForm", new VerifyCatalogFormAction());
		 	 	  
	}

	public final void init$$controllerBasicBackEndDefinitions() {
		init$$controllerBasicFrontEndDefinitions();
		actions.put("goToResponse", new GoToResponseAction());
		actions.put("verifySellerForm", new VerifySellerFormAction());
		actions.put("processSellerForm", new ProcessSellerFormAction());
		 	 	  
	}

	public void init() {
		init$$controllerBasicBackEndDefinitions();
		actions.put("goToCheckout", new GoToCheckoutAction());
		actions.put("verifyCheckoutForm", new VerifyCheckoutFormAction());
		actions.put("processCheckoutForm", new ProcessCheckoutFormAction());
		 	 	  
	}

	public void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String actionRequestParameter = request.getParameter(ACTION_IDENTIFIER);

		if (null == actionRequestParameter) {
			actionRequestParameter = "goToHome";
		}

		ControllerAction command = (ControllerAction) actions.get(actionRequestParameter);

		if (null == command) {
			throw new IllegalArgumentException("No command for form action: " + actionRequestParameter);
		}
		command.execute(request, response);
	}
}

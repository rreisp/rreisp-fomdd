package controller.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToCatalogAction implements ControllerAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServletContext context = request.getSession().getServletContext();
		try {
		    context.getRequestDispatcher("/catalog.jsp").forward(request, response);
		} catch (Exception e) {
	 	   e.printStackTrace();
		}
    }
}

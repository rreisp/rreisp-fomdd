
package model.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import model.Order;
import model.repository.OrderRepository;

public class NewOrderAction implements ModelAction {

    Object params[];
    private OrderRepository orderRepository;

    public NewOrderAction() {
    
    }

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        ServletContext context = request.getSession().getServletContext();
        orderRepository = (OrderRepository) context.getAttribute("orderRepository");
        setParams(request);

        // TODO This part you'll need to write by hand. Sorry!
        // Create (Persist) your order using orderRepository
        Order object = orderRepository.newOrder();
		
        // return your persistent Entity;
        return object;
    }

    @Override
    public void setParams(HttpServletRequest request) {
        // TODO This part you'll need to write by hand. Sorry!
        // Get the order fields from request and put in params[] array
		
    }
}

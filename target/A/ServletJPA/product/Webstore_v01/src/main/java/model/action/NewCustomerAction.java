
package model.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import model.Customer;
import model.repository.CustomerRepository;

public class NewCustomerAction implements ModelAction {

    Object params[];
    private CustomerRepository customerRepository;

    public NewCustomerAction() {
    
    }

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        ServletContext context = request.getSession().getServletContext();
        customerRepository = (CustomerRepository) context.getAttribute("customerRepository");
        setParams(request);

        // TODO This part you'll need to write by hand. Sorry!
        // Create (Persist) your customer using customerRepository
        Customer object = customerRepository.newCustomer();
		
        // return your persistent Entity;
        return object;
    }

    @Override
    public void setParams(HttpServletRequest request) {
        // TODO This part you'll need to write by hand. Sorry!
        // Get the customer fields from request and put in params[] array
		
    }
}

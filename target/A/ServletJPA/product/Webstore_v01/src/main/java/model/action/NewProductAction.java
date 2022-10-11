
package model.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import model.Product;
import model.repository.ProductRepository;

public class NewProductAction implements ModelAction {

    Object params[];
    private ProductRepository productRepository;

    public NewProductAction() {
    
    }

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        ServletContext context = request.getSession().getServletContext();
        productRepository = (ProductRepository) context.getAttribute("productRepository");
        setParams(request);

        // TODO This part you'll need to write by hand. Sorry!
        // Create (Persist) your product using productRepository
        Product object = productRepository.newProduct();
		
        // return your persistent Entity;
        return object;
    }

    @Override
    public void setParams(HttpServletRequest request) {
        // TODO This part you'll need to write by hand. Sorry!
        // Get the product fields from request and put in params[] array
		
    }
}


package model.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import model.Seller;
import model.repository.SellerRepository;

public class NewSellerAction implements ModelAction {

    Object params[];
    private SellerRepository sellerRepository;

    public NewSellerAction() {
    
    }

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        ServletContext context = request.getSession().getServletContext();
        sellerRepository = (SellerRepository) context.getAttribute("sellerRepository");
        setParams(request);

        // TODO This part you'll need to write by hand. Sorry!
        // Create (Persist) your seller using sellerRepository
        Seller object = sellerRepository.newSeller();
		
        // return your persistent Entity;
        return object;
    }

    @Override
    public void setParams(HttpServletRequest request) {
        // TODO This part you'll need to write by hand. Sorry!
        // Get the seller fields from request and put in params[] array
		
    }
}

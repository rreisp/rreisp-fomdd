package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "t_seller")
public class Seller implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public static class Attribute {
		// TODO: This part you'll need to write by hand. Sorry!
		// Complete with more relation attributes that aren't listed here
		// Composed attributes need to be added.
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String sellerName;
	
	private String sellerAddress;
	
	private String sellerMail;

	@OneToOne(mappedBy=Product.Attribute.SELLER)
	private Product sellerProduct;
	
	public Seller() {
		// Used by JPA.
	}

	public Seller(String sellerName, String sellerAddress, String sellerMail) {
		this.sellerName = (String) sellerName;
		this.sellerAddress = (String) sellerAddress;
		this.sellerMail = (String) sellerMail;		
	}

	public Long getId() {
		return id;
	}
		
	public String getSellerAddress() { 	
		return this.sellerAddress;
	}
		
	public String getSellerMail() { 	
		return this.sellerMail;
	}
		
	public String getSellerName() { 	
		return this.sellerName;
	}
		
	public Product getSellerProduct() { 	
		return this.sellerProduct;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSellerAddress(String sellerAddress) {	
		this.sellerAddress = sellerAddress;
	}
	
	public void setSellerMail(String sellerMail) {	
		this.sellerMail = sellerMail;
	}
	
	public void setSellerName(String sellerName) {	
		this.sellerName = sellerName;
	}
	
	public void setSellerProduct(Product sellerProduct) {	
		this.sellerProduct = sellerProduct;
	}
	
}

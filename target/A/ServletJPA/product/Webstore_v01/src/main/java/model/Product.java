package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "t_product")
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public static class Attribute {
		public final static String ORDER = "productOrder";
		public final static String SELLER = "productSeller";
		// TODO: This part you'll need to write by hand. Sorry!
		// Complete with more relation attributes that aren't listed here
		// Composed attributes need to be added.
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String productName;
	
	private String productDescription;
	
	private Double productPrice;
	
	@ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
	private Order productOrder;
	
	
	@OneToOne(mappedBy=Seller.Attribute.PRODUCT)
	private Seller productSeller;
	
	
	public Product() {
		// Used by JPA.
	}

	public Product(String productName, String productDescription, Double productPrice, Order productOrder, Seller productSeller) {
		this.productName = (String) productName;
		this.productDescription = (String) productDescription;
		this.productPrice = (Double) productPrice;
		this.productOrder = (Order) productOrder;
		this.productSeller = (Seller) productSeller;		
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
		
	public String getProductName() { 	
		return this.productName;
	}
	
	public void setProductName(String productName) {	
		this.productName = productName;
	}
		
	public String getProductDescription() { 	
		return this.productDescription;
	}
	
	public void setProductDescription(String productDescription) {	
		this.productDescription = productDescription;
	}
		
	public Double getProductPrice() { 	
		return this.productPrice;
	}
	
	public void setProductPrice(Double productPrice) {	
		this.productPrice = productPrice;
	}
		
	public Order getProductOrder() { 	
		return this.productOrder;
	}
	
	public void setProductOrder(Order productOrder) {	
		this.productOrder = productOrder;
	}
		
	public Seller getProductSeller() { 	
		return this.productSeller;
	}
	
	public void setProductSeller(Seller productSeller) {	
		this.productSeller = productSeller;
	}
	
}

package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "t_order")
public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public static class Attribute {
		public final static String CUSTOMER = "orderCustomer";
		// TODO: This part you'll need to write by hand. Sorry!
		// Complete with more relation attributes that aren't listed here
		// Composed attributes need to be added.
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
	private Customer orderCustomer;

	@OneToMany(targetEntity = Product.class, mappedBy = Product.Attribute.ORDER, fetch = FetchType.LAZY)
	private List orderProduct;

	@OneToOne(mappedBy=Payment.Attribute.ORDER)
	private Payment orderPayment;
	
	
	public Order() {
		// Used by JPA.
	}

	public Order(Customer orderCustomer) {
		this.orderCustomer = (Customer) orderCustomer;		
	}

	public Long getId() {
		return id;
	}
		
	public Customer getOrderCustomer() { 	
		return this.orderCustomer;
	}
		
	public Payment getOrderPayment() { 	
		return this.orderPayment;
	}
		
	public List getOrderProduct() { 	
		return this.orderProduct;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setOrderCustomer(Customer orderCustomer) {	
		this.orderCustomer = orderCustomer;
	}
	
	public void setOrderPayment(Payment orderPayment) {	
		this.orderPayment = orderPayment;
	}
	
	public void setOrderProduct(List orderProduct) {	
		this.orderProduct = orderProduct;
	}
	
}

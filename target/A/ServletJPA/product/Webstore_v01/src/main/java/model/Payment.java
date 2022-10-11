package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "t_payment")
public class Payment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public static class Attribute {
		public final static String ORDER = "paymentOrder";
		// TODO: This part you'll need to write by hand. Sorry!
		// Complete with more relation attributes that aren't listed here
		// Composed attributes need to be added.
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String paymentStatus;
	
	@OneToOne(mappedBy=Order.Attribute.PAYMENT)
	private Order paymentOrder;
	
	
	public Payment() {
		// Used by JPA.
	}

	public Payment(String paymentStatus, Order paymentOrder) {
		this.paymentStatus = (String) paymentStatus;
		this.paymentOrder = (Order) paymentOrder;		
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
		
	public String getPaymentStatus() { 	
		return this.paymentStatus;
	}
	
	public void setPaymentStatus(String paymentStatus) {	
		this.paymentStatus = paymentStatus;
	}
		
	public Order getPaymentOrder() { 	
		return this.paymentOrder;
	}
	
	public void setPaymentOrder(Order paymentOrder) {	
		this.paymentOrder = paymentOrder;
	}
	
}

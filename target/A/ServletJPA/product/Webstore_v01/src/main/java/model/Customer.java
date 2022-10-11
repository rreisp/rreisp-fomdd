package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "t_customer")
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public static class Attribute {
		public final static String ORDER = "customerOrder";
		// TODO: This part you'll need to write by hand. Sorry!
		// Complete with more relation attributes that aren't listed here
		// Composed attributes need to be added.
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String customerName;
	
	private String customerAddress;
	
	private String customerMail;
	
	@OneToMany(targetEntity = Order.class, mappedBy = Order.Attribute.CUSTOMER, fetch = FetchType.LAZY)
	private List customerOrder;
	
	
	public Customer() {
		// Used by JPA.
	}

	public Customer(String customerName, String customerAddress, String customerMail, List customerOrder) {
		this.customerName = (String) customerName;
		this.customerAddress = (String) customerAddress;
		this.customerMail = (String) customerMail;
		this.customerOrder = (List) customerOrder;			
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
		
	public String getCustomerName() { 	
		return this.customerName;
	}
	
	public void setCustomerName(String customerName) {	
		this.customerName = customerName;
	}
		
	public String getCustomerAddress() { 	
		return this.customerAddress;
	}
	
	public void setCustomerAddress(String customerAddress) {	
		this.customerAddress = customerAddress;
	}
		
	public String getCustomerMail() { 	
		return this.customerMail;
	}
	
	public void setCustomerMail(String customerMail) {	
		this.customerMail = customerMail;
	}
		
	public List getCustomerOrder() { 	
		return this.customerOrder;
	}
	
	public void setCustomerOrder(List customerOrder) {	
		this.customerOrder = customerOrder;
	}
	
}

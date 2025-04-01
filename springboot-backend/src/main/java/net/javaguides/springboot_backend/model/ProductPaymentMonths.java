package net.javaguides.springboot_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_payment_months")
public class ProductPaymentMonths {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="month_name")
	private String monthName;
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false) // Foreign key column
	private Product productId;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false) // Foreign key column
	private LoginUser userId;
	
	public ProductPaymentMonths() {
		
	}
	public ProductPaymentMonths(String monthName) {
		super();
		this.monthName=monthName;
	}
	
	public void setId(long id) {
		this.id=id;
	}public long getId() {
		return id;
	}
	public void setMonthName(String monthName) {
		this.monthName=monthName;
	}public String getMonthName() {
		return monthName;
	}
	public void setProductId(Product productId) {
		this.productId=productId;
	}public Product getProductId() {
		return productId;
	}
	public void setUserId(LoginUser userId) {
		this.userId=userId;
	}public LoginUser getUserId() {
		return userId;
	}
}

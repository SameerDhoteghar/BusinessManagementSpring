package net.javaguides.springboot_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="product")
	private String product;
	@Column(name="monthly_payment")
	private String monthlyPayment;
	@Column(name="contract_start_date")
	private String contractStartDate;
	@Column(name="contract_end_date")
	private String contractEndDate;
	@Column(name="payment_due_date")
	private String paymentDueDate;
	@Column(name="product_price")
	private long productPrice;
	@Column(name="amount_paid")
	private long amountPaid;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false) // Foreign key column
	private Employee employee;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false) // Foreign key column
	private LoginUser userId;
	
	public Product() {
		
	}
	public Product(String product, String monthlyPayment, String contractStartDate, String contractEndDate, String paymentDueDate,long productPrice,long amountPaid) {
		super();
		this.product=product;
		this.monthlyPayment=monthlyPayment;
		this.contractStartDate=contractStartDate;
		this.contractEndDate=contractEndDate;
		this.paymentDueDate=paymentDueDate;
		this.productPrice=productPrice;
		this.amountPaid=amountPaid;
	}
	public void setId(long id) {
		this.id = id;
	}public long getId() {
		return id;
	}
	public void setProduct(String product) {
		this.product = product;
	}public String getProduct() {
		return product;
	}
	public void setMonthlyPayment(String monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}public String getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}public String getContractStartDate() {
		return contractStartDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}public String getContractEndDate() {
		return contractEndDate;
	}
	public void setPaymentDueDate(String paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}public String getPaymentDueDate() {
		return paymentDueDate;
	}
	
	public void setEmployee(Employee employee) {
	    this.employee = employee;
	}public Employee getEmployee() {
	    return employee;
	}
	public void setUserId(LoginUser userId) {
		this.userId = userId;
	}public LoginUser getUserId() {
		return userId;
	}
	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}public long getProductPrice() {
		return productPrice;
	}
	public void setAmountPaid(long amountPaid) {
		this.amountPaid = amountPaid;
	}public long getAmountPaid() {
		return amountPaid;
	}
}

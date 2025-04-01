package net.javaguides.springboot_backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employee_id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email_id")
	private String emailId;
	@Column(name="contact_no")
	private long contactNo;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false) // Foreign key column
	private LoginUser userId;
	
//	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
//	//@JsonManagedReference  // 🔥 Employee is the parent
//    private List<Product> products;
	
	public Employee() {
		
	}
	public Employee(String firstName,String lastName, String emailId,long contactNo) {
		super();
		this.firstName=firstName;
		this.lastName=lastName;
		this.emailId=emailId;
		this.contactNo=contactNo;
	}
	
	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
	}public long getEmployee_id() {
		return employee_id;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}public String getfirstName() {
		return firstName;
	}
	public void setlastName(String lastName) {
		this.lastName=lastName;
	}public String getlastName() {
		return lastName;
	}
	public void setemailId(String emailId) {
		this.emailId=emailId;
	}public String getemailId() {
		return emailId;
	}
	public void setcontactNo(long contactNo) {
		this.contactNo = contactNo;
	}public long getcontactNo() {
		return contactNo;
	}
	public void setUserId(LoginUser userId) {
		this.userId = userId;
	}public LoginUser getUserId() {
		return userId;
	}
	
//	public void setProducts(List<Product> products) {
//		this.products=products;
//	}public List<Product> getProducts(){
//		return products;
//	}
}

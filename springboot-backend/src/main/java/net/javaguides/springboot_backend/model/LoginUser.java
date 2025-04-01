package net.javaguides.springboot_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class LoginUser {

	@Id
	@Column(name="userId")
	private String userId;
	@Column(name="password")
	private String password;
	
	public LoginUser() {
		
	}
	
	public LoginUser(String userId, String password) {
		super();
		this.userId=userId;
		this.password=password;
	}
//	
//	public void setLogin_id(long login_id) {
//		this.login_id=login_id;
//	}public long login_id() {
//		return login_id;
//	}
	public void setUserId(String userId) {
		this.userId=userId;
	}public String getUserId() {
		return userId;
	}
	public void setPassword(String password) {
		this.password=password;
	}public String getPassword() {
		return password;
	}
	
}

package org.jsp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long phone;
	private String password;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public List<Product> getMobile() {
//		return mobile;
//	}
//	public void setMobile(List<Product> mobile) {
//		this.mobile = mobile;
//	}
	
//	@OneToMany
//	private List<Product> mobile;
	
	
	
	@Override
	public String toString() {
		
		
		return "\nUser : \n id \t= " + id + "\n name \t= " + name + "\n email \t= " + email + "\n phone \t= " + phone + "\n password \t= "+password ;

	}
	
}

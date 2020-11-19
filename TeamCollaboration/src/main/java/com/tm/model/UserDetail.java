package com.tm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="user_detail")
public class UserDetail implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userdetail_id", unique = true, nullable = false)
    private Long userdetail_id;

	 @Column(name = "full_name")
     private String full_name;
	 
	

	@Column(name = "email")
	 private String email;
	 
	 @Column(name = "nic")
	 private String nic;
	 
	 @Column(name = "telephone")
	 private String telephone;
	 
	 @Column(name = "designation")
	 private String designation;
	 
	 @Column(name = "address")
	 private String address;
	 
	 @Column(name = "detail")
	 private String detail;

	

	@OneToOne(mappedBy="user_detail")
	private Users users;
	 
	 
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Long getUserdetail_id() {
		return userdetail_id;
	}

	public void setUserdetail_id(Long userdetail_id) {
		this.userdetail_id = userdetail_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	 public String getDetail() {
			return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

	
	
	
}

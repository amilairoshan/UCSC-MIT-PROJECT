package com.tm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
	public UserRole() {
		super();
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    private Long role_id;

	 @Column(name = "role_name")
    private String role_name;
    
	 @Column(name = "insert_date")
    private Date insert_date;
    
	 @Column(name = "modified_date")
    private Date modified_date;
    
	 @ManyToMany(fetch = FetchType.EAGER,
	            cascade = {
	                CascadeType.ALL
	            },
	            mappedBy = "user_role")
	    private Set<Users> users = new HashSet();
	 
   
	public Long getRole_id() {
		return role_id;
	}


	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}


	public String getRole_name() {
		return role_name;
	}


	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}


	public Date getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public UserRole(String role_name, Date insert_date, Date modified_date) {
		super();
		this.role_name = role_name;
		this.insert_date = insert_date;
		this.modified_date = modified_date;
	}
    
    

   
   

    
   

  
   
}

package com.tm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class Users implements Serializable {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Long id;
	
    @Column(name="username")
	private String userName;
    
    @Column(name="password")
	private String password;
    
    @Column(name="isactive")
	private boolean isActive;
    
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.ALL
            })
    @JoinTable(name = "user_role_map",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<UserRole> user_role = new HashSet();
    
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="user_detail")
    private UserDetail user_detail;
    
   
	public UserDetail getUser_detail() {
		return user_detail;
	}
	
	public void setUser_detail(UserDetail user_detail) {
		this.user_detail = user_detail;
	}
	public Set<UserRole> getUser_role() {
		return user_role;
	}
	public void setUser_role(Set<UserRole> user_role) {
		this.user_role = user_role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	
	
	
}

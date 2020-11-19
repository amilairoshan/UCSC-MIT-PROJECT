package com.tm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "mt_designation")
public class MTDesignation implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desination_id", unique = true, nullable = false)
    private Long desination_id;

	 @Column(name = "desination_name")
    private String desination_name;

	public Long getDesination_id() {
		return desination_id;
	}

	public void setDesination_id(Long desination_id) {
		this.desination_id = desination_id;
	}

	public String getDesination_name() {
		return desination_name;
	}

	public void setDesination_name(String desination_name) {
		this.desination_name = desination_name;
	}
	
	
	
	
}

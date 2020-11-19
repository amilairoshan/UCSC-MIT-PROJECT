package com.tm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "mt_projectlifecycle")
public class MTProjectLifeCycle implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lifecycle_id", unique = true, nullable = false)
    private Long lifecycle_id;

	 @Column(name = "lifecycle_name")
    private String lifecycle_name;

	public Long getLifecycle_id() {
		return lifecycle_id;
	}

	public void setLifecycle_id(Long lifecycle_id) {
		this.lifecycle_id = lifecycle_id;
	}

	public String getLifecycle_name() {
		return lifecycle_name;
	}

	public void setLifecycle_name(String lifecycle_name) {
		this.lifecycle_name = lifecycle_name;
	}
	 
	 


}

package com.tm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projectLeads")
public class projectLeads implements Serializable {

	public projectLeads() {
		super();
		// TODO Auto-generated constructor stub
	}

	public projectLeads(Project projectlead, Long lead_id) {
		super();
		this.projectlead = projectlead;
		this.lead_id = lead_id;
	}

	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Long id;

	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectlead;
	
	@Column(name = "lead_id")
	private Long lead_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProjectlead() {
		return projectlead;
	}

	public void setProjectlead(Project projectlead) {
		this.projectlead = projectlead;
	}

	public Long getLead_id() {
		return lead_id;
	}

	public void setLead_id(Long lead_id) {
		this.lead_id = lead_id;
	}

	
	
	
	
	
	
	
	
	
	
	
}

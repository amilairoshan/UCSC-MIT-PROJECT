package com.tm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "project")
public class Project implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Long id;
	
    @Column(name="projectname")
	private String projectname;
    
    @Column(name="projectkey")
	private String projectkey;
    
    @Column(name="projectdetail")
	private String projectdetail;
	
    @Column(name = "start_date")
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date start_date;
    
	 @Column(name = "end_date")
	 @JsonFormat(pattern="MM/dd/yyyy")
    private Date end_date;
	 
	 @Column(name = "staus")
	 private String staus;
	
	 @Column(name = "lifecycle")
	 private String lifecycle;

	 @Column(name = "projectprogress")
	 private String projectprogress;
	 
	 @OneToMany(mappedBy = "project", fetch = FetchType.LAZY,
     cascade = CascadeType.ALL)
	 
	 Set<projectManagers> projectManagers;
	 
	 
	 @OneToMany(mappedBy = "projectlead", fetch = FetchType.LAZY,
	 cascade = CascadeType.ALL)
			 
	Set<projectLeads> projectLeads;
	

	@OneToMany(mappedBy = "projectmembers", fetch = FetchType.LAZY,
			 cascade = CascadeType.ALL)
					 
	Set<projectMembers> projectMembers;
	
	
	
	public Set<projectMembers> getProjectMembers() {
		return projectMembers;
	}

	public void setProjectMembers(Set<projectMembers> projectMembers) {
		this.projectMembers = projectMembers;
	}

	public Set<projectLeads> getProjectLeads() {
		return projectLeads;
	}

	public void setProjectLeads(Set<projectLeads> projectLeads) {
		this.projectLeads = projectLeads;
	}

	public Set<projectManagers> getProjectManagers() {
		return projectManagers;
	}

	public void setProjectManagers(Set<projectManagers> projectManagers) {
		this.projectManagers = projectManagers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectkey() {
		return projectkey;
	}

	public void setProjectkey(String projectkey) {
		this.projectkey = projectkey;
	}

	public String getProjectdetail() {
		return projectdetail;
	}

	public void setProjectdetail(String projectdetail) {
		this.projectdetail = projectdetail;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getStaus() {
		return staus;
	}

	
	public void setStaus(String staus) {
		this.staus = staus;
	}

	public String getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(String lifecycle) {
		this.lifecycle = lifecycle;
	}

	public String getProjectprogress() {
		return projectprogress;
	}

	public void setProjectprogress(String projectprogress) {
		this.projectprogress = projectprogress;
	}
	 
	
	
}

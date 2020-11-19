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
@Table(name = "projectMembers")
public class projectMembers implements Serializable {

	
	public projectMembers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public projectMembers(Project projectmembers, Long member_id) {
		super();
		this.projectmembers = projectmembers;
		this.member_id = member_id;
	}

	
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectmembers;
	
	@Column(name = "member_id")
	private Long member_id;

	public Project getProjectmembers() {
		return projectmembers;
	}

	public void setProjectmembers(Project projectmembers) {
		this.projectmembers = projectmembers;
	}

	public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}


	
}

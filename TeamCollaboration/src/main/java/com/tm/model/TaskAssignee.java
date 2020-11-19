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
@Table(name = "task_assignee")
public class TaskAssignee implements Serializable {

	
	public TaskAssignee(Task taskAssignee) {
		super();
		this.taskAssignee = taskAssignee;
	}

	public TaskAssignee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskAssignee(Task taskAssignee, Long member_id) {
		super();
		this.taskAssignee = taskAssignee;
		this.member_id = member_id;
	}

	
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task taskAssignee;
	
	@Column(name = "member_id")
	private Long member_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Task getTaskAssignee() {
		return taskAssignee;
	}

	public void setTaskAssignee(Task taskAssignee) {
		this.taskAssignee = taskAssignee;
	}

	public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	
	
	
	
	
	
	
	
}

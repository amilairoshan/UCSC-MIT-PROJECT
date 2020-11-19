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
@Table(name = "task_reporter")
public class TaskReporter implements Serializable {

	
	public TaskReporter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskReporter(Task taskReporter, Long member_id) {
		super();
		this.taskReporter = taskReporter;
		this.member_id = member_id;
	}

	
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task taskReporter;
	
	@Column(name = "member_id")
	private Long member_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Task getTaskReporter() {
		return taskReporter;
	}

	public void setTaskReporter(Task taskReporter) {
		this.taskReporter = taskReporter;
	}

	public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	
	
}

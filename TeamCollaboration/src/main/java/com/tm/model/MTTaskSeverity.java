package com.tm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mt_task_severity")
public class MTTaskSeverity implements Serializable {

	public MTTaskSeverity(Long severity_id, List<Task> task) {
		super();
		this.severity_id = severity_id;
		this.task = task;
	}

	public MTTaskSeverity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MTTaskSeverity(Long severity_id) {
		super();
		this.severity_id = severity_id;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "severity_id", unique = true, nullable = false)
    private Long severity_id;

	@Column(name = "severity_name")
    private String severity_name;

	@OneToMany(mappedBy="task_severity",cascade = CascadeType.ALL)
	private List<Task> task;
	
	
	


	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}

	public Long getSeverity_id() {
		return severity_id;
	}

	public void setSeverity_id(Long severity_id) {
		this.severity_id = severity_id;
	}

	public String getSeverity_name() {
		return severity_name;
	}

	public void setSeverity_name(String severity_name) {
		this.severity_name = severity_name;
	}
	
	
	 
	
	
}

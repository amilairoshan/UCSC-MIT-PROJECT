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
@Table(name = "mt_task_status")
public class MTTaskStatus implements Serializable {
	
	public MTTaskStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MTTaskStatus(Long status_id) {
		super();
		this.status_id = status_id;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", unique = true, nullable = false)
    private Long status_id;

	@Column(name = "status_name")
    private String status_name;

	@OneToMany(mappedBy="task_status",cascade = CascadeType.ALL)
	private List<Task> task;
	
	
	
	
	
	

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}

	public Long getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Long status_id) {
		this.status_id = status_id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
	

}

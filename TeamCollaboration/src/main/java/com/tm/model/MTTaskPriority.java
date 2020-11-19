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
@Table(name = "mt_task_priority")
public class MTTaskPriority implements Serializable {

	public MTTaskPriority() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MTTaskPriority(Long priority_id) {
		super();
		this.priority_id = priority_id;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_id", unique = true, nullable = false)
    private Long priority_id;

	@Column(name = "priority_name")
    private String priority_name;

	@OneToMany(mappedBy="task_prority",cascade = CascadeType.ALL)
	private List<Task> task;
	

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}

	public Long getPriority_id() {
		return priority_id;
	}

	public void setPriority_id(Long priority_id) {
		this.priority_id = priority_id;
	}

	public String getPriority_name() {
		return priority_name;
	}

	public void setPriority_name(String priority_name) {
		this.priority_name = priority_name;
	}
	
	
	
	
	
	
	
	
}

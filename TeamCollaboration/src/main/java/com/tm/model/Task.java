package com.tm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "task")
public class Task implements Serializable{

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="task_status")
    private MTTaskStatus task_status;

	
	@Column(name = "project")
	private String project;
	
	@Column(name = "task_type")
	private Long task_type;
	

	@ManyToOne(optional = false)
    @JoinColumn(name="task_severity")
    private MTTaskSeverity task_severity;

	@ManyToOne(optional = false)
    @JoinColumn(name="task_prority")
    private MTTaskPriority task_prority;

	 @Column(name = "summary")
	 private String summary;
	 
	 @Column(name = "due_date")
	 @JsonFormat(pattern="MM/dd/yyyy")
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date due_date;
	 
	
	 @OneToMany(mappedBy = "taskReporter", fetch = FetchType.LAZY,
			 cascade = CascadeType.ALL)				 
	Set<TaskReporter> taskReporter;
	 
	 @OneToMany(mappedBy = "taskAssignee", fetch = FetchType.LAZY,
			 cascade = CascadeType.ALL)				 
	Set<TaskAssignee> taskAssignee;
	
	 @Column(name = "description")
	 private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MTTaskStatus getTask_status() {
		return task_status;
	}

	public void setTask_status(MTTaskStatus task_status) {
		this.task_status = task_status;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Long getTask_type() {
		return task_type;
	}

	public void setTask_type(Long task_type) {
		this.task_type = task_type;
	}
	
	
	
	public MTTaskSeverity getTask_severity() {
		return task_severity;
	}

	public void setTask_severity(MTTaskSeverity task_severity) {
		this.task_severity = task_severity;
	}

	public MTTaskPriority getTask_prority() {
		return task_prority;
	}

	public void setTask_prority(MTTaskPriority task_prority) {
		this.task_prority = task_prority;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Set<TaskReporter> getTaskReporter() {
		return taskReporter;
	}

	public void setTaskReporter(Set<TaskReporter> taskReporter) {
		this.taskReporter = taskReporter;
	}

	public Set<TaskAssignee> getTaskAssignee() {
		return taskAssignee;
	}

	public void setTaskAssignee(Set<TaskAssignee> taskAssignee) {
		this.taskAssignee = taskAssignee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	 
	 
	 
	 
	 
	 
	
	
	
	
	
}

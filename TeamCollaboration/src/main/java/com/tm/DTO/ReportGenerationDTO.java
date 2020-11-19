package com.tm.DTO;

import java.io.Serializable;

public class ReportGenerationDTO implements Serializable {

	
	private String project;
	private Long priority;
	private Long severity;
	private Long status;
	private Long tasktype;
	
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Long getPriority() {
		return priority;
	}
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	public Long getSeverity() {
		return severity;
	}
	public void setSeverity(Long severity) {
		this.severity = severity;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getTasktype() {
		return tasktype;
	}
	public void setTasktype(Long tasktype) {
		this.tasktype = tasktype;
	}

	
	
	
	
	
	
	
}

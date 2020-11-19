package com.tm.DTO;

import java.io.Serializable;

public class ReportResultDTO implements Serializable {
	
	private String id;
	private String project;
	private String pririty;
	private String severity;
	private String status;
	private String type;
	private String duedate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getPririty() {
		return pririty;
	}
	public void setPririty(String pririty) {
		this.pririty = pririty;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	
	
	
	

}

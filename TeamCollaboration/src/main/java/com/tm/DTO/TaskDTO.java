package com.tm.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tm.model.MTEventType;
import com.tm.model.UserDetail;
import com.tm.model.Users;

public class TaskDTO implements Serializable {

		private Long taskId;
		
		private Long task_status;
		
		private String project;
		
		private String task_type;
		
		private Long task_severity;

		private Long task_prority;

		private String summary;

		private String description;

		@JsonFormat(pattern="MM/dd/yyyy")
		private Date due_date;
		
		private Set<Long> taskReporter;
		
		private Set<Long> taskAssignee;
		
		/*@JsonDeserialize(as = TaskDTO.class)
		private MultipartFile files;
		*/
		

	/*	public MultipartFile getFiles() {
			return files;
		}

		public void setFiles(MultipartFile files) {
			this.files = files;
		}*/

		public Long getTaskId() {
			return taskId;
		}

		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}

		public Long getTask_status() {
			return task_status;
		}

		public void setTask_status(Long task_status) {
			this.task_status = task_status;
		}

		public String getProject() {
			return project;
		}

		public void setProject(String project) {
			this.project = project;
		}

		public String getTask_type() {
			return task_type;
		}

		public void setTask_type(String task_type) {
			this.task_type = task_type;
		}

		public Long getTask_severity() {
			return task_severity;
		}

		public void setTask_severity(Long task_severity) {
			this.task_severity = task_severity;
		}

		public Long getTask_prority() {
			return task_prority;
		}

		public void setTask_prority(Long task_prority) {
			this.task_prority = task_prority;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getDue_date() {
			return due_date;
		}

		public void setDue_date(Date due_date) {
			this.due_date = due_date;
		}

		public Set<Long> getTaskReporter() {
			return taskReporter;
		}

		public void setTaskReporter(Set<Long> taskReporter) {
			this.taskReporter = taskReporter;
		}

		public Set<Long> getTaskAssignee() {
			return taskAssignee;
		}

		public void setTaskAssignee(Set<Long> taskAssignee) {
			this.taskAssignee = taskAssignee;
		}

		

		
		
		
	
}

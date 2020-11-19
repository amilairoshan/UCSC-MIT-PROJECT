package com.tm.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tm.model.UserDetail;
import com.tm.model.Users;

public class ProjectDTO implements Serializable {

		private Long projectId;
		
	
		private String projectname;
	    
		private String projectkey;
	    
		private String staus;
			
		private String lifecycle;
		@JsonFormat(pattern="MM/dd/yyyy")
		private Date start_date;
		@JsonFormat(pattern="MM/dd/yyyy")
		private Date end_date;
		
		private Set<Long> projectManagers;
		 
		private  Set<Long> projectLeads;
		 
		private Set<Long> projectTeam;
		
		private String projectdetail;
		
		private String projectprogress;
		
		private List<String> projectManagersDetails;
		 
		private  List<String> projectLeadsDetails;
		 
		private List<String> projectTeamDetails;
		

		public List<String> getProjectManagersDetails() {
			return projectManagersDetails;
		}

		public void setProjectManagersDetails(List<String> projectManagersDetails) {
			this.projectManagersDetails = projectManagersDetails;
		}

		public List<String> getProjectLeadsDetails() {
			return projectLeadsDetails;
		}

		public void setProjectLeadsDetails(List<String> projectLeadsDetails) {
			this.projectLeadsDetails = projectLeadsDetails;
		}

		public List<String> getProjectTeamDetails() {
			return projectTeamDetails;
		}

		public void setProjectTeamDetails(List<String> projectTeamDetails) {
			this.projectTeamDetails = projectTeamDetails;
		}

		public Set<Long> getProjectManagers() {
			return projectManagers;
		}

		public void setProjectManagers(Set<Long> projectManagers) {
			this.projectManagers = projectManagers;
		}

		public Set<Long> getProjectLeads() {
			return projectLeads;
		}

		public void setProjectLeads(Set<Long> projectLeads) {
			this.projectLeads = projectLeads;
		}

		public Set<Long> getProjectTeam() {
			return projectTeam;
		}

		public void setProjectTeam(Set<Long> projectTeam) {
			this.projectTeam = projectTeam;
		}

		public Long getProjectId() {
			return projectId;
		}

		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}


		public String getProjectname() {
			return projectname;
		}

		public void setProjectname(String projectname) {
			this.projectname = projectname;
		}

		public String getProjectkey() {
			return projectkey;
		}

		public void setProjectkey(String projectkey) {
			this.projectkey = projectkey;
		}

		public String getStaus() {
			return staus;
		}

		public void setStaus(String staus) {
			this.staus = staus;
		}

		public String getLifecycle() {
			return lifecycle;
		}

		public void setLifecycle(String lifecycle) {
			this.lifecycle = lifecycle;
		}

		public Date getStart_date() {
			return start_date;
		}

		public void setStart_date(Date start_date) {
			this.start_date = start_date;
		}

		public Date getEnd_date() {
			return end_date;
		}

		public void setEnd_date(Date end_date) {
			this.end_date = end_date;
		}

	

		public String getProjectdetail() {
			return projectdetail;
		}

		public void setProjectdetail(String projectdetail) {
			this.projectdetail = projectdetail;
		}

		public String getProjectprogress() {
			return projectprogress;
		}

		public void setProjectprogress(String projectprogress) {
			this.projectprogress = projectprogress;
		}
		
		
		
		 
		 
	
	
	
	
	
}

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
import com.tm.model.MTEventType;
import com.tm.model.UserDetail;
import com.tm.model.Users;

public class MeetingDTO implements Serializable {

		private Long meetingId;
		
		private String meetingdetail;
	    
		@JsonFormat(pattern="MM/dd/yyyy")
		private Date start_date;
		
		private String start_time;

		@JsonFormat(pattern="MM/dd/yyyy")
		private Date end_date;
		
		private String end_time;

		
		private String venue;
		
		private String mtEventType;
		
		private List<Long> participants;
		
		
		
		
		public List<Long> getParticipants() {
			return participants;
		}

		public void setParticipants(List<Long> participants) {
			this.participants = participants;
		}

		public Long getMeetingId() {
			return meetingId;
		}

		public void setMeetingId(Long meetingId) {
			this.meetingId = meetingId;
		}

		public String getMeetingdetail() {
			return meetingdetail;
		}

		public void setMeetingdetail(String meetingdetail) {
			this.meetingdetail = meetingdetail;
		}

	
		public String getVenue() {
			return venue;
		}

		public void setVenue(String venue) {
			this.venue = venue;
		}

		public String getMtEventType() {
			return mtEventType;
		}

		public void setMtEventType(String mtEventType) {
			this.mtEventType = mtEventType;
		}

		public Date getStart_date() {
			return start_date;
		}

		public void setStart_date(Date start_date) {
			this.start_date = start_date;
		}

		public String getStart_time() {
			return start_time;
		}

		public void setStart_time(String start_time) {
			this.start_time = start_time;
		}

		public Date getEnd_date() {
			return end_date;
		}

		public void setEnd_date(Date end_date) {
			this.end_date = end_date;
		}

		public String getEnd_time() {
			return end_time;
		}

		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}

		

	

		
		 
		
	
	
	
}

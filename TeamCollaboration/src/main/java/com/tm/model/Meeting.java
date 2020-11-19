package com.tm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "meeting")
public class Meeting implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Long id;
	
    @Column(name="meetingdetail")
	private String meetingdetail;
	
    @Column(name = "start_date")
    @JsonFormat(pattern="MM/dd/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_date;
    
	 @Column(name = "end_date")
	 @JsonFormat(pattern="MM/dd/yyyy")
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date end_date;
	 
	 @Column(name = "venue")
	 private String venue;
	
	
	 
	 @ManyToOne
	 @JoinColumn(name="eventtype", nullable=false)
	 private MTEventType mtEventType;

	 @OneToMany(mappedBy = "meetingMembers", fetch = FetchType.LAZY,
			 cascade = CascadeType.ALL)				 
	Set<MeetingMembers> meetingMembers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeetingdetail() {
		return meetingdetail;
	}

	public void setMeetingdetail(String meetingdetail) {
		this.meetingdetail = meetingdetail;
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

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public MTEventType getMtEventType() {
		return mtEventType;
	}

	public void setMtEventType(MTEventType mtEventType) {
		this.mtEventType = mtEventType;
	}

	public Set<MeetingMembers> getMeetingMembers() {
		return meetingMembers;
	}

	public void setMeetingMembers(Set<MeetingMembers> meetingMembers) {
		this.meetingMembers = meetingMembers;
	}
	 
	
	
	

	
}

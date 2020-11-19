package com.tm.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "mt_eventtype")
public class MTEventType implements Serializable{
	
	public MTEventType() {
	
	}

	public MTEventType(Long event_id) {
		this.event_id = event_id;
	}

	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", unique = true, nullable = false)
    private Long event_id;

	 @Column(name = "event_name")
    private String event_name;

	 
	 @OneToMany(mappedBy="mtEventType")
	 private Set<Meeting> meeting;
	 
	/*@OneToOne(mappedBy="mtEventType")
	private Meeting meeting;*/
	 
	
	 
	public Long getEvent_id() {
		return event_id;
	}

	public Set<Meeting> getMeeting() {
		return meeting;
	}

	public void setMeeting(Set<Meeting> meeting) {
		this.meeting = meeting;
	}

	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	
	

}

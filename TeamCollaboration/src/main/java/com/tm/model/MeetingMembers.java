package com.tm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meetingMembers")
public class MeetingMembers implements Serializable {

	
	public MeetingMembers(Meeting meetingMembers, Long member_id) {
		super();
		this.meetingMembers = meetingMembers;
		this.member_id = member_id;
	}

	public MeetingMembers() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meetingMembers;
	
	@Column(name = "member_id")
	private Long member_id;

	

	
}

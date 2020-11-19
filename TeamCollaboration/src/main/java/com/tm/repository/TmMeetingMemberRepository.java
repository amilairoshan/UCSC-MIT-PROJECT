package com.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tm.model.MTEventType;
import com.tm.model.Meeting;
import com.tm.model.MeetingMembers;

public interface TmMeetingMemberRepository extends JpaRepository<MeetingMembers, Long> {

}

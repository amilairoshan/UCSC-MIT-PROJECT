package com.tm.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tm.model.MTEventType;
import com.tm.model.Meeting;

public interface TmMeetingRepository extends JpaRepository<Meeting, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update Meeting m set m.start_date =:start_date,m.end_date =:end_date,m.venue=:venue,m.meetingdetail=:meetingdetail where m.id =:eventId")
	void updateEvent(@Param("eventId") Long eventId, @Param("start_date") Date start_date,@Param("end_date") Date end_date,@Param("venue") String venue,@Param("meetingdetail") String meetingdetail);
	
	
	
}

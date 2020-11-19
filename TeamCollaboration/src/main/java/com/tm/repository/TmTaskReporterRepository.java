package com.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tm.model.MTEventType;
import com.tm.model.Meeting;
import com.tm.model.MeetingMembers;
import com.tm.model.TaskAssignee;
import com.tm.model.TaskReporter;

public interface TmTaskReporterRepository extends JpaRepository<TaskReporter, Long> {

	 @Transactional
	 @Modifying
	@Query("Delete FROM TaskReporter u WHERE u.taskReporter.id =:task_id")
	void deleteExistanceTask(@Param("task_id") Long task_id);
	
}

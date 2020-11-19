package com.tm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tm.model.Task;
import com.tm.model.Users;

public interface TmTaskRepository extends JpaRepository<Task, Long> {

	
	@Query("SELECT u FROM Task u WHERE u.task_type =:task_type")
	List<Task> findAllChangeRequests(@Param("task_type") Long task_type);
	
	
	@Query("SELECT u FROM Task u WHERE u.project =:project and u.task_prority.priority_id =:priority and u.task_severity.severity_id =:severity and u.task_status.status_id =:status and u.task_type =:type")
	List<Task> findAllTasksForReports(@Param("project") String project,@Param("priority") Long priority,@Param("severity") Long severity,@Param("status") Long status,@Param("type") Long type);
	
	
	
}

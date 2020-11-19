package com.tm.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tm.model.MTEventType;
import com.tm.model.MTTaskPriority;
import com.tm.model.MTTaskSeverity;
import com.tm.model.UserDetail;

public interface TmTaskPriorityRepository extends JpaRepository<MTTaskPriority, Long> {

	@Query("SELECT e FROM MTTaskPriority e")
	List<MTTaskPriority> findAllTaskPriority();
	
	
}

package com.tm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tm.model.MTTaskPriority;
import com.tm.model.MTTaskStatus;
import com.tm.model.Task;

public interface TmTaskStatusRepository extends JpaRepository<MTTaskStatus, Long> {

	@Query("SELECT e FROM MTTaskStatus e")
	List<MTTaskStatus> findAllTaskStatus();
}

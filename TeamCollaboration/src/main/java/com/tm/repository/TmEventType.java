package com.tm.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tm.model.MTEventType;
import com.tm.model.UserDetail;

public interface TmEventType extends JpaRepository<MTEventType, Long> {

	@Query("SELECT e FROM MTEventType e")
	List<MTEventType> findAllEvents();
	
	
}

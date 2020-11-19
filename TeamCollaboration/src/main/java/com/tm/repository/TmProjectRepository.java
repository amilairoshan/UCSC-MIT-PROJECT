package com.tm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tm.model.Project;
import com.tm.model.Users;

public interface TmProjectRepository extends JpaRepository<Project, Long> {

	@Query("SELECT u FROM Project u WHERE u.id=:id")
	Project findProjectById(@Param("id") Long id);
	
}

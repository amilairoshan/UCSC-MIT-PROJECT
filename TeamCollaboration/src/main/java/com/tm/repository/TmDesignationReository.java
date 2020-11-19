package com.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tm.model.MTDesignation;

@Repository
public interface TmDesignationReository extends JpaRepository<MTDesignation, Long> {

	
	
}

package com.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tm.model.MTProjectStaus;

public interface TmProjectStatusRepository extends JpaRepository<MTProjectStaus, Long> {

}

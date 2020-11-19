package com.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tm.model.projectLeads;

public interface TmProjectLeadsRepository extends JpaRepository<projectLeads, Long> {

}

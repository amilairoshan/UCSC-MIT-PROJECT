package com.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tm.model.MTProjectLifeCycle;

public interface TMLifeCycleRepository extends JpaRepository<MTProjectLifeCycle, Long> {

}

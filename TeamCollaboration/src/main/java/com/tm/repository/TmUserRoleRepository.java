package com.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tm.model.UserRole;
import com.tm.model.Users;
@Repository
public interface TmUserRoleRepository extends JpaRepository<UserRole, Long>{

	@Query("SELECT u FROM UserRole u WHERE u.role_id=:id")
	UserRole findUserRoleById(@Param("id") Long id);
	
}

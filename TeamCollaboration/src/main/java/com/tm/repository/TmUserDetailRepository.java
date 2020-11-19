package com.tm.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tm.model.UserDetail;
import com.tm.model.Users;
@Repository
public interface TmUserDetailRepository extends JpaRepository<UserDetail, Long>{

	
	@Query("SELECT u FROM UserDetail u WHERE u.users.id IN (:id)")
	List<UserDetail> findUserById(@Param("id") Set<Long> id);
	
	
}

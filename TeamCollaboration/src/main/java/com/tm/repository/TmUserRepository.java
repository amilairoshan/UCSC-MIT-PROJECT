package com.tm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tm.model.Users;
@Transactional
@Repository
public interface TmUserRepository extends JpaRepository<Users,Long> {

	Users findByUserName(String userName);
	
	@Query("SELECT u FROM Users u WHERE u.userName=:username")
	List<Users> findUserByUserName(@Param("username") String username);
	
	@Query("SELECT u FROM Users u WHERE u.id != 0")
	List<Users> findAllActiveUsers();
	
	
	@Modifying
	@Query("delete from Users u where u.id=:id")
	void deleteUsers(@Param("id") Long id);
	
	@Query("SELECT u FROM Users u WHERE u.id=:id")
	Users findUserById(@Param("id") Long id);
	
	@Query("SELECT u FROM Users u WHERE u.user_detail.designation=:designation")
	List<Users> findUsersForProjectCreation(@Param("designation") String designation);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update Users u set u.password =:password where u.id =:userId")
	void updatePassword(@Param("userId") Long userId, @Param("password") String password);
	
	
	
	
}

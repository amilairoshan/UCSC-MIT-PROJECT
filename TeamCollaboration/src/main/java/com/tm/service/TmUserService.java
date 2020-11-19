package com.tm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tm.DTO.UserPasswordChangeDTO;
import com.tm.DTO.UserRegisterDTO;
import com.tm.DTO.UsersDTO;
import com.tm.model.UserDetail;
import com.tm.model.UserRole;
import com.tm.model.Users;
import com.tm.repository.TmDesignationReository;
import com.tm.repository.TmUserRepository;

@Service
public class TmUserService {

	@Autowired
	private TmUserRepository tmUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private TmDesignationReository tmDesignationReository;
	
	
	public List<Users> getAllUsers() { 
		return tmUserRepository.findAllActiveUsers();
		
	}
	
	public List<Users> findUserByUserName(String username) { 
		return tmUserRepository.findUserByUserName(username);
		
	}
	
	
	public Users findByUserName(String username) { 
		return tmUserRepository.findByUserName(username);
		
	}
	
	public void updateUserPassword(UserPasswordChangeDTO userPasswordChangeDTO) { 
		Long userId=userPasswordChangeDTO.getUserId();
		String ecryptedpassword=passwordEncoder.encode(userPasswordChangeDTO.getNewPassword());
		
		tmUserRepository.updatePassword(userId, ecryptedpassword);
		
	}
	
	
	
	public void registerNewUser(UserRegisterDTO userRegisterDTO) {  
		Date date = new Date();
		Users u=new Users();
		String encodedPassword = passwordEncoder.encode(userRegisterDTO.getPassword());
		u.setUserName(userRegisterDTO.getUserName());
		u.setPassword(encodedPassword);
		u.setActive(true);
		
		UserRole ur=new UserRole("User",date, date);
		u.getUser_role().add(ur);
		ur.getUsers().add(u);
		
		UserDetail ud=new UserDetail();
		ud.setFull_name(userRegisterDTO.getFullName());
		ud.setEmail(userRegisterDTO.getEmail());
	
		u.setUser_detail(ud);
		ud.setUsers(u);

		tmUserRepository.save(u);
		//tmDesignationReository.findAll();
	}
	
	
	
	
	
	
}

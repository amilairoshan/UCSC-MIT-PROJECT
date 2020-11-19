package com.tm.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tm.DTO.ResponseDTO;
import com.tm.DTO.UsersDTO;
import com.tm.model.MTDesignation;
import com.tm.model.UserDetail;
import com.tm.model.UserRole;
import com.tm.model.Users;
import com.tm.repository.TmDesignationReository;
import com.tm.repository.TmUserDetailRepository;
import com.tm.repository.TmUserRepository;
import com.tm.repository.TmUserRoleRepository;
@Service
public class TmUserDetailService implements UserDetailsService {
	
	@Autowired
	private TmUserRepository tmUserRepository;
	
	@Autowired
	private TmUserRoleRepository tmUserRoleRepository;
	
	@Autowired
	private TmDesignationReository tmDesignationReository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TmUserDetailRepository tmUserDetailRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Users us=tmUserRepository.findByUserName(userName);
		if(us==null)
			throw new UsernameNotFoundException("User Not On Board");
		
	
		return new UserPrincipal(us);
	}

	public List<MTDesignation> getUserDesignations() {
		return tmDesignationReository.findAll();	
	}
	
	public List<UserRole> getUserRole() {
		return tmUserRoleRepository.findAll();	
	}
	
	
	public void saveNewUser(UsersDTO usersdto) {  
		Date date = new Date();
		Users u=new Users();
		String encodedPassword = passwordEncoder.encode(usersdto.getPassword());
		u.setUserName(usersdto.getUserName());
		u.setPassword(encodedPassword);
		u.setActive(true);
		
		UserRole ur=new UserRole(usersdto.getUserRole1(),date, date);
		u.getUser_role().add(ur);
		ur.getUsers().add(u);
		//UserRole ur1=new UserRole("User",date, date);
		
	/*	if(usersdto.getUserRole1()== "true") { 
			u.getUser_role().add(ur);
			ur.getUsers().add(u);
		}
		if(usersdto.getUserRole2()== "true") { 
			u.getUser_role().add(ur1);
			ur1.getUsers().add(u);
		}*/
		UserDetail ud=new UserDetail();
		ud.setFull_name(usersdto.getFullName());
		ud.setEmail(usersdto.getEmail());
		ud.setNic(usersdto.getNic());
		ud.setTelephone(usersdto.getTpNumber());
		ud.setDesignation(usersdto.getDesignation());
		ud.setAddress(usersdto.getAddress());
		ud.setDetail(usersdto.getDetail());
		
		
		u.setUser_detail(ud);
		ud.setUsers(u);
		
		
		tmUserRepository.save(u);
	}
	
	
	public List<Users> getAllUsers() {
		return tmUserRepository.findAllActiveUsers();
	}
	
	public void deleteUsers(Long id) { 
		tmUserRepository.deleteById(id);
	}
	
	public Users viewUser(Long id) { 	
		return tmUserRepository.findUserById(id);	
	}
	
	
	
	public void updateUser(UsersDTO usersdto) {  
		Date date = new Date();
		Users u=new Users();
		u.setId(usersdto.getUserId());
		//String encodedPassword = passwordEncoder.encode(usersdto.getPassword());
		u.setUserName(usersdto.getUserName());
		u.setActive(true);
		Users udi=tmUserRepository.findUserById(usersdto.getUserId());
		u.setPassword(udi.getPassword());
		
		Set<UserRole> set = udi.getUser_role();

		UserRole ur=new UserRole(usersdto.getUserRole1(),date, date);
//		UserRole ur1=new UserRole("User",date, date);
		
		for (UserRole item: set) {
		ur.setRole_id(item.getRole_id());	
		}
		u.getUser_role().add(ur);	
		ur.getUsers().add(u);
		/*if(usersdto.getUserRole1()== "true") { 
			u.getUser_role().add(ur);
			ur.getUsers().add(u);
		}
		if(usersdto.getUserRole2()== "true") { 
			u.getUser_role().add(ur1);
			ur1.getUsers().add(u);
		}*/
		UserDetail ud=new UserDetail();
		ud.setUserdetail_id(udi.getUser_detail().getUserdetail_id());
		ud.setFull_name(usersdto.getFullName());
		ud.setEmail(usersdto.getEmail());
		ud.setNic(usersdto.getNic());
		ud.setTelephone(usersdto.getTpNumber());
		ud.setDesignation(usersdto.getDesignation());
		ud.setAddress(usersdto.getAddress());
		ud.setDetail(usersdto.getDetail());
		
		
		u.setUser_detail(ud);
		ud.setUsers(u);
		
		
		tmUserRepository.save(u);
	}
	
	
	public List<UserDetail> getUsersDetails(Set<Long> userId){
		return tmUserDetailRepository.findUserById(userId);
	}
	
	public void updateUserProfile(UsersDTO usersdto) {  
		Date date = new Date();
		Users u=new Users();
		u.setId(usersdto.getUserId());
		u.setUserName(usersdto.getUserName());
		u.setActive(true);
		Users udi=tmUserRepository.findUserById(usersdto.getUserId());
		u.setPassword(udi.getPassword());
		Set<UserRole> set = udi.getUser_role();

		UserRole ur=new UserRole(udi.getUser_role().parallelStream().findFirst().get().getRole_name(),date, date);
		
		for (UserRole item: set) {
		ur.setRole_id(item.getRole_id());	
		}
		u.getUser_role().add(ur);	
		ur.getUsers().add(u);
	
		UserDetail ud=new UserDetail();
		ud.setUserdetail_id(udi.getUser_detail().getUserdetail_id());
		ud.setFull_name(usersdto.getFullName());
		ud.setEmail(usersdto.getEmail());
		ud.setNic(usersdto.getNic());
		ud.setTelephone(usersdto.getTpNumber());
		ud.setDesignation(usersdto.getDesignation());
		ud.setAddress(usersdto.getAddress());
		ud.setDetail(usersdto.getDetail());
		
		
		u.setUser_detail(ud);
		ud.setUsers(u);
		
		
		tmUserRepository.save(u);
	}
	
	
	
	
	
}

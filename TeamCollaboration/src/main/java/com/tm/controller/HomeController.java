package com.tm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tm.DTO.ProjectDTO;
import com.tm.DTO.ResponseDTO;
import com.tm.DTO.UserPasswordChangeDTO;
import com.tm.DTO.UserRegisterDTO;
import com.tm.DTO.UsersDTO;
import com.tm.DTO.UsersLogingInfoDTO2;
import com.tm.model.Project;
import com.tm.model.UserDetail;
import com.tm.model.Users;
import com.tm.repository.TmUserRepository;
import com.tm.service.TmProjectService;
import com.tm.service.TmUserDetailService;
import com.tm.service.TmUserService;

import org.springframework.http.MediaType;

@Controller
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
public class HomeController {
	@Autowired
	private TmUserDetailService tmUserDetailService;
	
	@Autowired
	private TmProjectService tmProjectService;
	
	@Autowired
	private TmUserService tmUserService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	public UsersLogingInfoDTO2 getUserLoginInfo() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UsersLogingInfoDTO2 loginginfo=new UsersLogingInfoDTO2();
		if(null != securityContext.getAuthentication()){
			
			String userName=securityContext.getAuthentication().getName();
			Users u=tmUserService.findByUserName(userName);
			String roleName=u.getUser_role().parallelStream().findFirst().get().getRole_name();
			loginginfo.setUsername(userName);
			loginginfo.setUserrole(roleName);
			return loginginfo;
			
		}
		return loginginfo;
	}
	
	
	
	
	@RequestMapping(value={"", "/", "TeamCollaboration","login"}, method = RequestMethod.GET)
	public ModelAndView  index() {
		Map<String, Object> params = new HashMap();
	      return new ModelAndView("pages/login", params);
	}
	
	@RequestMapping(value={"/register"}, method = RequestMethod.GET)
	public ModelAndView  register() {
		Map<String, Object> params = new HashMap();
		UserRegisterDTO registerForm=new UserRegisterDTO();
		params.put("userRegisterForm",registerForm);	
	      return new ModelAndView("pages/register", params);
	}
	
	
	@RequestMapping(value = "/userregister",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView userRegister(Model model,@ModelAttribute("userRegisterForm") @Validated UserRegisterDTO userRegisterDTO, BindingResult result,final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap();
		try {
			if(!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword()) ) { 
			UserRegisterDTO registerForm=new UserRegisterDTO();
			params.put("userRegisterForm",registerForm);
			params.put("regErrorMessage","The password and confirmation password do not match");
			return new ModelAndView("pages/register", params);
			}
			if(!tmUserService.findUserByUserName(userRegisterDTO.getUserName()).isEmpty()) {
				UserRegisterDTO registerForm=new UserRegisterDTO();
				params.put("userRegisterForm",registerForm);
				params.put("regErrorMessage","User Name Already Exists");
				return new ModelAndView("pages/register", params);
				
			}
			else { 
				tmUserService.registerNewUser(userRegisterDTO);
				return new ModelAndView("pages/login", params);
			}
			
	} catch (Exception e) {
		e.printStackTrace();
		UserRegisterDTO registerForm=new UserRegisterDTO();
		params.put("userRegisterForm",registerForm);
		params.put("regErrorMessage","Error Occured");
		 return new ModelAndView("pages/register", params);
	}	
	}
	
	
	
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	
@RequestMapping(value={"/home"}, method = RequestMethod.GET)
	public ModelAndView postLogin(RedirectAttributes atts) {
		Map<String, Object> params = new HashMap();
		
		params.put("projectstatus",tmProjectService.getProjectStaus() );	
		params.put("projectlifecycle", tmProjectService.getProjectLifeCycle());	
		params.put("projectmanagers", tmProjectService.getProjectManagers("Project Manager"));
		params.put("teamlead", tmProjectService.getTeamLeads("Technical Lead"));
		params.put("teammembers", tmUserService.getAllUsers());
		params.put("project", tmProjectService.getAllProjects());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if(null != securityContext.getAuthentication()){
		String userName=securityContext.getAuthentication().getName();
		Users u=tmUserService.findByUserName(userName);
		String roleName=u.getUser_role().parallelStream().findFirst().get().getRole_name();
		params.put("role", roleName);
		params.put("loginUser", userName);	
		if(roleName.equalsIgnoreCase("admin")) {
				params.put("page", "Projects");
				return new ModelAndView("pages/home", params);	
			}else {
				params.put("page", "ProjectsUser");
				return new ModelAndView("pages/userhome", params);
			}
			
		}
		params.put("page", "ProjectsUser");
		return new ModelAndView("pages/userhome", params);
	}

	
////////////////////////////////////User Related Controllers ///////////////////////////////


	@RequestMapping(value={"/users"}, method = RequestMethod.GET)
	public ModelAndView postUsersLogin(RedirectAttributes atts) {
		Map<String, Object> params = new HashMap();
		params.put("page", "Users");
		params.put("designation", tmUserDetailService.getUserDesignations());	
		params.put("userlist", tmUserDetailService.getAllUsers());	
		params.put("role", getUserLoginInfo().getUserrole());
		params.put("loginUser", getUserLoginInfo().getUsername());	
		if(getUserLoginInfo().getUserrole().equalsIgnoreCase("admin")) {
			return new ModelAndView("pages/home", params);	
		}else {
			return new ModelAndView("pages/userhome", params);
		}
		
	//	return new ModelAndView("pages/home", params);
	}
	
	@RequestMapping(value = "/usersSave",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO performLogin(@RequestBody UsersDTO usersdto, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			if(tmUserService.findUserByUserName(usersdto.getUserName()).isEmpty()) { 
				tmUserDetailService.saveNewUser(usersdto);
				res.setStatus(true);
				
			}else {
				res.setStatus(false);
			}
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	@RequestMapping(value = "/userDelete",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO userDelete(@RequestBody Long userId, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmUserDetailService.deleteUsers(userId);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		 return res;
	}	
	}
	
	@RequestMapping(value = "/userView",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO userView(@RequestBody Long userId, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			UsersDTO usersdto=new UsersDTO();
			Users u=tmUserDetailService.viewUser(userId);
			usersdto.setFullName(u.getUser_detail().getFull_name());
			usersdto.setNic(u.getUser_detail().getNic());
			usersdto.setTpNumber(u.getUser_detail().getTelephone());
			usersdto.setUserRole1(u.getUser_role().parallelStream().findFirst().get().getRole_name()); 
			/*if(u.getUser_role() != null) {  
				u.getUser_role()
			    .forEach(i -> {
			        if (i.getRole_name().equalsIgnoreCase("Admin")) {
			        	usersdto.setUserRole1("true");  
			        }else  {
			        	usersdto.setUserRole2("true");  
			        }
			    });
			}*/
			usersdto.setAddress(u.getUser_detail().getAddress());
			usersdto.setEmail(u.getUser_detail().getEmail());
			usersdto.setUserName(u.getUserName());
			usersdto.setPassword(u.getPassword());
			usersdto.setDesignation(u.getUser_detail().getDesignation());
			usersdto.setDetail(u.getUser_detail().getDetail());
			res.setData(usersdto);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		 return res;
	}	
	}
	
	@RequestMapping(value = "/updateUser",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateUser(@RequestBody UsersDTO usersdto, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmUserDetailService.updateUser(usersdto);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	//////////////////////////////////////Project Related Controllers ///////////////////////////////////////////
	
	
	@RequestMapping(value = "/projectSave",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO performLogin(@RequestBody ProjectDTO projectdto, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmProjectService.saveNewProject(projectdto);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	
	@RequestMapping(value = "/projectView",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO projectView(@RequestBody Long projectId, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			ProjectDTO projectdto=new ProjectDTO();
			Project u=tmProjectService.findProjectById(projectId);
			projectdto.setProjectname(u.getProjectname());
			projectdto.setProjectkey(u.getProjectkey());
			projectdto.setLifecycle(u.getLifecycle());
			projectdto.setStaus(u.getStaus());
			projectdto.setStart_date(u.getStart_date());
			projectdto.setEnd_date(u.getEnd_date());
			
			HashSet setManager = new HashSet();
			u.getProjectManagers().parallelStream().forEach(x -> setManager.add(x.getManager_id()));
			
			HashSet setLead = new HashSet();
			u.getProjectLeads().parallelStream().forEach(x -> setLead.add(x.getLead_id()));
			
			HashSet setTeam = new HashSet();
			u.getProjectMembers().parallelStream().forEach(x -> setTeam.add(x.getMember_id()));
			
		
	        List<UserDetail> managers = tmUserDetailService.getUsersDetails(setManager);
	        List<String> mn=new LinkedList<>();
	        managers.parallelStream().forEach(m->mn.add(m.getFull_name()));
			projectdto.setProjectManagersDetails(mn);
			
	        List<UserDetail> leads = tmUserDetailService.getUsersDetails(setLead);
	        List<String> ld=new LinkedList<>();
	        leads.parallelStream().forEach(m->ld.add(m.getFull_name()));
			projectdto.setProjectLeadsDetails(ld);
			
			 List<UserDetail> members = tmUserDetailService.getUsersDetails(setTeam);
		     List<String> tmmembers=new LinkedList<>();
		     members.parallelStream().forEach(m->tmmembers.add(m.getFull_name()));
			 projectdto.setProjectTeamDetails(tmmembers);
			projectdto.setProjectdetail(u.getProjectdetail());
		
			res.setData(projectdto);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		 return res;
	}	
	}
	
	@RequestMapping(value = "/projectDelete",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO projectDelete(@RequestBody Long projectId, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmProjectService.deleteProject(projectId);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		 return res;
	}	
	}
	
	@RequestMapping(value = "/updateProject",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateUser(@RequestBody ProjectDTO projectDTO, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmProjectService.updateProject(projectDTO);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	/////////////////////////////////My Profile Controllers ///////////////////////////////////////////////////////////
	
	
	@RequestMapping(value={"/myProfile"}, method = RequestMethod.GET)
	public ModelAndView myProfile(RedirectAttributes atts) {
		Map<String, Object> params = new HashMap();
		params.put("page", "MyProfile");
		params.put("designation", tmUserDetailService.getUserDesignations());	
		params.put("userinfo",tmUserService.findByUserName(getUserLoginInfo().getUsername()) );	
		params.put("role", getUserLoginInfo().getUserrole());
		params.put("loginUser", getUserLoginInfo().getUsername());	
		
		return new ModelAndView("pages/userhome", params);

	}
	
	
	@RequestMapping(value = "/updateUserProfile",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateUserProfile(@RequestBody UsersDTO usersdto, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmUserDetailService.updateUserProfile(usersdto);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	@RequestMapping(value = "/updateUserPassword",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateUserPassword(@RequestBody UserPasswordChangeDTO userspassworddto, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmUserService.updateUserPassword(userspassworddto);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	

	/*@RequestMapping(value={"/designation"}, method = RequestMethod.GET)
	public ModelAndView  register() {
		Map<String, Object> params = new HashMap();
		UserRegisterDTO registerForm=new UserRegisterDTO();
		params.put("userRegisterForm",registerForm);	
	      return new ModelAndView("pages/register", params);
	}
	*/
	
	
	
}

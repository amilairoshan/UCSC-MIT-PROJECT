package com.tm.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tm.DTO.MeetingDTO;
import com.tm.DTO.ReportGenerationDTO;
import com.tm.DTO.ResponseDTO;
import com.tm.DTO.TaskDTO;
import com.tm.DTO.UsersDTO;
import com.tm.DTO.UsersLogingInfoDTO2;
import com.tm.model.Meeting;
import com.tm.model.Task;
import com.tm.model.TaskReporter;
import com.tm.model.Users;
import com.tm.repository.TmTaskPriorityRepository;
import com.tm.repository.TmTaskSeverityRepository;
import com.tm.repository.TmTaskStatusRepository;
import com.tm.service.TmProjectService;
import com.tm.service.TmTaskService;
import com.tm.service.TmUserDetailService;
import com.tm.service.TmUserService;

@Controller
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
public class ReportController {

	@Autowired
	private TmProjectService tmProjectService;
	
	@Autowired
	private TmTaskService tmTaskService;
	
	
	@Autowired
	private TmTaskPriorityRepository tmTaskPriorityRepository;
	
	@Autowired
	private TmTaskSeverityRepository tmTaskSeverityRepository;
	
	@Autowired
	private TmTaskStatusRepository tmTaskStatusRepository;
	
	@Autowired
	private TmUserService tmUserService;
	
	@Autowired
	private TmUserDetailService tmUserDetailService;
	
////////////////////////////////////////////////////Reports /////////////////////////////////////////////////////////////////////////////
	
	
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
	
	
	@RequestMapping(value={"/reports"}, method = RequestMethod.GET)
	public ModelAndView postUsersLogin(RedirectAttributes atts) {
		Map<String, Object> params = new HashMap();
		params.put("page", "reports");
		params.put("project", tmProjectService.getAllProjects());
		params.put("severity", tmTaskSeverityRepository.findAll());
		params.put("priority", tmTaskPriorityRepository.findAll());
		params.put("users", tmUserDetailService.getAllUsers());
		params.put("status", tmTaskStatusRepository.findAll());
		params.put("task", tmTaskService.findAllChangeRequests((long) 1));
		params.put("role", getUserLoginInfo().getUserrole());
		params.put("loginUser", getUserLoginInfo().getUsername());	
	
		return new ModelAndView("pages/home", params);	

	}
	
	    
	@RequestMapping(value = "/reportResult",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateUserProfile(@RequestBody ReportGenerationDTO reportGenerationDTO, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			res.setData(tmTaskService.findTaskForReports(reportGenerationDTO));
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}

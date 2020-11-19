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
import com.tm.DTO.ResponseDTO;
import com.tm.DTO.TaskDTO;
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
public class TaskController {

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
	
	private static String UPLOADED_FOLDER = "G://msc//TeamCollaborationFileUploader";
	
	
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
	
	
	@RequestMapping(value={"/ChangeRequest"}, method = RequestMethod.GET)
	public ModelAndView getCR(RedirectAttributes atts) {
		Map<String, Object> params = new HashMap();
		
		params.put("page", "ChangeRequest");
		params.put("project", tmProjectService.getAllProjects());
		params.put("severity", tmTaskSeverityRepository.findAll());
		params.put("priority", tmTaskPriorityRepository.findAll());
		params.put("users", tmUserDetailService.getAllUsers());
		params.put("status", tmTaskStatusRepository.findAll());
		params.put("task", tmTaskService.findAllChangeRequests((long) 1));
		params.put("role", getUserLoginInfo().getUserrole());
		params.put("loginUser", getUserLoginInfo().getUsername());	
		if(getUserLoginInfo().getUserrole().equalsIgnoreCase("admin")) {
			
			return new ModelAndView("pages/home", params);	
		}else {
			
			return new ModelAndView("pages/userhome", params);
		}
		//return new ModelAndView("pages/home", params);
	}
	
	@RequestMapping(value = "/changeRequestSave",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO saveCR(@RequestBody TaskDTO taskDTO, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmTaskService.saveCr(taskDTO);
			 res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	
	
	  //save file
	    private void saveUploadedFiles(MultipartFile files) throws IOException {

	            byte[] bytes = files.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + files.getOriginalFilename());
	            Files.write(path, bytes);

	    }
	
	    @RequestMapping(value = "/taskDelete",method = RequestMethod.POST)
		@ResponseBody
		public ResponseDTO eventDelete(@RequestBody Long taskId, HttpServletRequest request, HttpServletResponse response) {
			ResponseDTO res=new ResponseDTO();
			try {
				tmTaskService.deleteTask(taskId);
				res.setStatus(true);
				return res;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(false);
			 return res;
		}	
		}
		
	    @RequestMapping(value = "/taskView",method = RequestMethod.POST)
		@ResponseBody
		public ResponseDTO taskView(@RequestBody Long taskId, HttpServletRequest request, HttpServletResponse response) {
			ResponseDTO res=new ResponseDTO();
			try {
				MeetingDTO meetingdto=new MeetingDTO();
				TaskDTO taskDTO=new TaskDTO();
				
				Task t=tmTaskService.findTaskById(taskId);
				
				taskDTO.setProject(t.getProject());
				taskDTO.setTask_type(t.getTask_type().toString());
				taskDTO.setTask_severity(t.getTask_severity().getSeverity_id());
				taskDTO.setTask_prority(t.getTask_prority().getPriority_id());
				taskDTO.setTask_status(t.getTask_status().getStatus_id());
				
				taskDTO.setDue_date(t.getDue_date());
				taskDTO.setDescription(t.getDescription());
				
				HashSet setReporter = new HashSet();
				t.getTaskReporter().parallelStream().forEach(s -> setReporter.add(s.getMember_id()) );
				taskDTO.setTaskReporter(setReporter);
				
				HashSet setAssignee = new HashSet();
				t.getTaskAssignee().parallelStream().forEach(a->setAssignee.add(a.getMember_id()) );
				taskDTO.setTaskAssignee(setAssignee);
				
				
				res.setData(taskDTO);
				res.setStatus(true);
				return res;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(false);
			 return res;
		}	
		}
	
	    
	    @RequestMapping(value = "/changeRequestUpdate",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseDTO updateCR(@RequestBody TaskDTO taskDTO, HttpServletRequest request, HttpServletResponse response) {
			ResponseDTO res=new ResponseDTO();
			try {
				tmTaskService.updateCr(taskDTO);
				 res.setStatus(true);
				return res;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(false);
			return res;
		}	
		}
	    
	//////////////////////////////////////////////// Defects //////////////////////////////////////////////////////////  
	    
	    @RequestMapping(value={"/Defect"}, method = RequestMethod.GET)
		public ModelAndView getDefects(RedirectAttributes atts) {
			Map<String, Object> params = new HashMap();
			
			params.put("page", "Defect");
			params.put("project", tmProjectService.getAllProjects());
			params.put("severity", tmTaskSeverityRepository.findAll());
			params.put("priority", tmTaskPriorityRepository.findAll());
			params.put("users", tmUserDetailService.getAllUsers());
			params.put("status", tmTaskStatusRepository.findAll());
			params.put("task", tmTaskService.findAllChangeRequests((long) 2));
			params.put("role", getUserLoginInfo().getUserrole());
			params.put("loginUser", getUserLoginInfo().getUsername());	
			if(getUserLoginInfo().getUserrole().equalsIgnoreCase("admin")) {
				
				return new ModelAndView("pages/home", params);	
			}else {
				
				return new ModelAndView("pages/userhome", params);
			}
			
			//return new ModelAndView("pages/home", params);
		}  
	    
	    
	    @RequestMapping(value = "/defectSave",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseDTO saveDefect(@RequestBody TaskDTO taskDTO, HttpServletRequest request, HttpServletResponse response) {
			ResponseDTO res=new ResponseDTO();
			try {
				tmTaskService.saveDefect(taskDTO);
				 res.setStatus(true);
				return res;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(false);
			return res;
		}	
		} 
	    
	    @RequestMapping(value = "/defectUpdate",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseDTO updateDefect(@RequestBody TaskDTO taskDTO, HttpServletRequest request, HttpServletResponse response) {
			ResponseDTO res=new ResponseDTO();
			try {
				tmTaskService.updateDefect(taskDTO); 
				 res.setStatus(true);
				return res;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(false);
			return res;
		}	
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}

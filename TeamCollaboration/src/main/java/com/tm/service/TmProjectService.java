package com.tm.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.DTO.ProjectDTO;
import com.tm.DTO.UsersDTO;
import com.tm.model.MTProjectLifeCycle;
import com.tm.model.MTProjectStaus;
import com.tm.model.Project;
import com.tm.model.UserDetail;
import com.tm.model.UserRole;
import com.tm.model.Users;
import com.tm.model.projectLeads;
import com.tm.model.projectManagers;
import com.tm.model.projectMembers;
import com.tm.repository.TMLifeCycleRepository;
import com.tm.repository.TmProjectLeadsRepository;
import com.tm.repository.TmProjectManagersRepository;
import com.tm.repository.TmProjectMembersRepository;
import com.tm.repository.TmProjectRepository;
import com.tm.repository.TmProjectStatusRepository;
import com.tm.repository.TmUserRepository;
import com.tm.utill.projectProgress;

@Service
public class TmProjectService {
	private static final Logger LOGGER = LogManager.getLogger(TmProjectService.class);	
	@Autowired
	private TmProjectRepository tmProjectRepository;
	
	@Autowired
	private TmProjectStatusRepository tmProjectStatusRepository;
	
	@Autowired
	private TMLifeCycleRepository tmLifeCycleRepository;
	
	@Autowired
	private TmUserRepository tmUserRepository;
	
	@Autowired
	private TmProjectManagersRepository tmProjectManagersRepository;
	
	@Autowired
	private TmProjectLeadsRepository tmProjectLeadsRepository;
	
	@Autowired
	private TmProjectMembersRepository tmProjectMembersRepository;
	
	public List<MTProjectStaus> getProjectStaus(){ 
		return tmProjectStatusRepository.findAll();	
	}
	
	public List<MTProjectLifeCycle> getProjectLifeCycle(){ 
		return tmLifeCycleRepository.findAll();	
	}
	
	public List<Users> getProjectManagers(String Designation){
		return  tmUserRepository.findUsersForProjectCreation(Designation); 
	}
	
	public List<Users> getTeamLeads(String Designation){
		return  tmUserRepository.findUsersForProjectCreation(Designation); 
	}
	
	
	public void saveNewProject(ProjectDTO projectdto) {  
		Date date = new Date();
		Project p=new Project();
		
		p.setProjectname(projectdto.getProjectname());
		p.setProjectkey(projectdto.getProjectkey());
		p.setStaus(projectdto.getStaus());
		p.setLifecycle(projectdto.getLifecycle());
		p.setStart_date(projectdto.getStart_date());
		p.setEnd_date(projectdto.getEnd_date());
		p.setProjectdetail(projectdto.getProjectdetail());
		p.setProjectprogress(this.getProjectProgress(projectdto.getStaus()));
	
		tmProjectRepository.save(p);
		projectdto.getProjectManagers().parallelStream().forEach(s ->
		tmProjectManagersRepository.save(new projectManagers(p,s))
		);
		projectdto.getProjectLeads().parallelStream().forEach(s ->
		tmProjectLeadsRepository.save(new projectLeads(p,s))
		);
		projectdto.getProjectTeam().parallelStream().forEach(s ->
		tmProjectMembersRepository.save(new projectMembers(p,s))
		);
		
	}
	
	private String getProjectProgress(String projectStatus) { 
	String result="0";
	switch (projectStatus) {
	case "RequirementGathering":
		result= "10";
		break;
	case "FeasibiltyStudy":
		result= "20";
		break;
	case "Design":
		result= "30";
		break;
	case "Implementation":
		result= "40";
		break;
	case "Tesing":
		result= "60";
		break;
	case "BugFixing":
		result= "80";
		break;
	case "Completed":
		result= "100";
		break;
	case "Closed":
		result= "0";
		break;
	case "OnHold":
		result= "0";
		break;
	default:
		break;
	}
	return result;
	}

	
	public List<Project> getAllProjects(){
		return tmProjectRepository.findAll();
	}
	
	public Project findProjectById(Long Id) { 
		return tmProjectRepository.getOne(Id);
	}
	
	public void deleteProject(Long projectid) { 
		tmProjectRepository.deleteById(projectid);
	}
	
	public void updateProject(ProjectDTO projectdto) {  
		Date date = new Date();
		Project p=new Project();
		p.setId(projectdto.getProjectId());
		p.setProjectname(projectdto.getProjectname());
		p.setProjectkey(projectdto.getProjectkey());
		p.setStaus(projectdto.getStaus());
		p.setLifecycle(projectdto.getLifecycle());
		p.setStart_date(projectdto.getStart_date());
		p.setEnd_date(projectdto.getEnd_date());
		p.setProjectdetail(projectdto.getProjectdetail());
		p.setProjectprogress(this.getProjectProgress(projectdto.getStaus()));
	
		tmProjectRepository.save(p);
		projectdto.getProjectManagers().parallelStream().forEach(s ->
		tmProjectManagersRepository.save(new projectManagers(p,s))
		);
		projectdto.getProjectLeads().parallelStream().forEach(s ->
		tmProjectLeadsRepository.save(new projectLeads(p,s))
		);
		projectdto.getProjectTeam().parallelStream().forEach(s ->
		tmProjectMembersRepository.save(new projectMembers(p,s))
		);
		
	}
	
	
}

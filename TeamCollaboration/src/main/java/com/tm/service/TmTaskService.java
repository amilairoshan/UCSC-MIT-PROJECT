package com.tm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.DTO.ReportGenerationDTO;
import com.tm.DTO.ReportResultDTO;
import com.tm.DTO.TaskDTO;
import com.tm.model.MTEventType;
import com.tm.model.MTTaskPriority;
import com.tm.model.MTTaskSeverity;
import com.tm.model.MTTaskStatus;
import com.tm.model.Meeting;
import com.tm.model.MeetingMembers;
import com.tm.model.Task;
import com.tm.model.TaskAssignee;
import com.tm.model.TaskReporter;
import com.tm.repository.TmTaskAssigneeRepository;
import com.tm.repository.TmTaskReporterRepository;
import com.tm.repository.TmTaskRepository;
import com.tm.repository.TmTaskSeverityRepository;

@Service
public class TmTaskService {

	@Autowired
	private TmTaskRepository tmTaskRepository;
	
	@Autowired
	private TmTaskAssigneeRepository tmTaskAssigneeRepository;
	
	@Autowired
	private TmTaskReporterRepository tmTaskReporterRepository;
	
	public void saveCr(TaskDTO td) {
		
		Task t=new Task();
		t.setProject(td.getProject());
		MTTaskStatus st=new MTTaskStatus((long) 1);
		t.setTask_status(st);
		t.setTask_type((long) 1);
		t.setTask_severity(new MTTaskSeverity(td.getTask_severity()));
		t.setTask_prority(new MTTaskPriority(td.getTask_prority()));
		t.setDescription(td.getDescription());
		t.setDue_date(td.getDue_date());
		
		tmTaskRepository.save(t);
		
		td.getTaskAssignee().parallelStream().forEach(s->tmTaskAssigneeRepository.save(new TaskAssignee(t, s)) );
		
		td.getTaskReporter().parallelStream().forEach(r->tmTaskReporterRepository.save(new TaskReporter(t, r)) );

	
		
		
	}
	 
	public List<Task> findAllChangeRequests(Long task_type){
		
		return tmTaskRepository.findAllChangeRequests(task_type);
	}
	
	public void deleteTask(Long taskid) {
		tmTaskRepository.deleteById(taskid);
		
	}
	
	public Task findTaskById(Long Id) { 
		return tmTaskRepository.getOne(Id);
	}
	
	public void updateCr(TaskDTO td) {
		
		Task t=new Task();
		t.setId(td.getTaskId());
		t.setProject(td.getProject());
		MTTaskStatus st=new MTTaskStatus(td.getTask_status());
		t.setTask_status(st);
		t.setTask_type((long) 1);
		t.setTask_severity(new MTTaskSeverity(td.getTask_severity()));
		t.setTask_prority(new MTTaskPriority(td.getTask_prority()));
		t.setDescription(td.getDescription());
		t.setDue_date(td.getDue_date());
		
		tmTaskRepository.save(t);
		tmTaskAssigneeRepository.deleteExistanceTask(t.getId());
		td.getTaskAssignee().parallelStream().forEach(s->tmTaskAssigneeRepository.save(new TaskAssignee(t, s)) );
		
		tmTaskReporterRepository.deleteExistanceTask(t.getId());
		td.getTaskReporter().parallelStream().forEach(r->tmTaskReporterRepository.save(new TaskReporter(t, r)) );

	
		
		
	}
	
	/////////////////////////////////// Defect /////////////////////////////////////////////////////
	
public void saveDefect(TaskDTO td) {
		
		Task t=new Task();
		t.setProject(td.getProject());
		MTTaskStatus st=new MTTaskStatus((long) 1);
		t.setTask_status(st);
		t.setTask_type((long)2);
		t.setTask_severity(new MTTaskSeverity(td.getTask_severity()));
		t.setTask_prority(new MTTaskPriority(td.getTask_prority()));
		t.setDescription(td.getDescription());
		t.setDue_date(td.getDue_date());
		
		tmTaskRepository.save(t);
		
		td.getTaskAssignee().parallelStream().forEach(s->tmTaskAssigneeRepository.save(new TaskAssignee(t, s)) );
		
		td.getTaskReporter().parallelStream().forEach(r->tmTaskReporterRepository.save(new TaskReporter(t, r)) );

	
		
		
	}
	
public void updateDefect(TaskDTO td) {
	
	Task t=new Task();
	t.setId(td.getTaskId());
	t.setProject(td.getProject());
	MTTaskStatus st=new MTTaskStatus(td.getTask_status());
	t.setTask_status(st);
	t.setTask_type((long)2);
	t.setTask_severity(new MTTaskSeverity(td.getTask_severity()));
	t.setTask_prority(new MTTaskPriority(td.getTask_prority()));
	t.setDescription(td.getDescription());
	t.setDue_date(td.getDue_date());
	
	tmTaskRepository.save(t);
	tmTaskAssigneeRepository.deleteExistanceTask(t.getId());
	td.getTaskAssignee().parallelStream().forEach(s->tmTaskAssigneeRepository.save(new TaskAssignee(t, s)) );
	
	tmTaskReporterRepository.deleteExistanceTask(t.getId());
	td.getTaskReporter().parallelStream().forEach(r->tmTaskReporterRepository.save(new TaskReporter(t, r)) );


	
	
}

/////////////////////////////////////////////Report Results ////////////////////////////////////////////////////

public List<ReportResultDTO> findTaskForReports(ReportGenerationDTO reportGenerationDTO) { 
	
	List<Task> taslList= tmTaskRepository.findAllTasksForReports(reportGenerationDTO.getProject(), reportGenerationDTO.getPriority(), reportGenerationDTO.getSeverity(), reportGenerationDTO.getStatus(), reportGenerationDTO.getTasktype());
	
	List<ReportResultDTO> list=new ArrayList<ReportResultDTO>();  
	for(Task t:taslList) {
		ReportResultDTO r=new ReportResultDTO();
		r.setId(t.getId().toString());
		r.setPririty(t.getTask_prority().getPriority_name());
		r.setProject(t.getProject());
		r.setSeverity(t.getTask_severity().getSeverity_name());
		r.setStatus(t.getTask_status().getStatus_name());
		r.setDuedate(t.getDue_date().toString());
		list.add(r);
	}
	
return list;


}






}

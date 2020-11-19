package com.tm.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.DTO.MeetingDTO;
import com.tm.DTO.ProjectDTO;
import com.tm.model.MTEventType;
import com.tm.model.Meeting;
import com.tm.model.MeetingMembers;
import com.tm.model.Project;
import com.tm.model.projectLeads;
import com.tm.model.projectManagers;
import com.tm.model.projectMembers;
import com.tm.repository.TmEventType;
import com.tm.repository.TmMeetingMemberRepository;
import com.tm.repository.TmMeetingRepository;

@Service
public class TmMeetingService {
	
	@Autowired
	private TmMeetingRepository tmMeetingRepository;
	
	@Autowired
	private TmMeetingMemberRepository tmMeetingMemberRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(TmMeetingService.class);	
	
	public void saveNewMeeting(MeetingDTO meetingDTO) throws ParseException {  
		

		
		Timestamp ts=new Timestamp(meetingDTO.getStart_date().getTime());  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        String sdate=formatter.format(ts)+" "+meetingDTO.getStart_time()+":00";
        SimpleDateFormat formatforsdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date strdate=formatforsdate.parse(sdate); 
		
     

       Timestamp te=new Timestamp(meetingDTO.getEnd_date().getTime());  
        SimpleDateFormat formattere = new SimpleDateFormat("yyyy-MM-dd");  
        String edate=formattere.format(te)+" "+meetingDTO.getEnd_time()+":00";
        SimpleDateFormat formatforedate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date enddate=formatforedate.parse(edate);
		
		

		Meeting m=new Meeting();
		MTEventType me=new MTEventType();
		me.setEvent_id(Long.valueOf(meetingDTO.getMtEventType()));
		
		m.setVenue(meetingDTO.getVenue());
		m.setStart_date(strdate);
		m.setEnd_date(enddate);
		m.setMeetingdetail(meetingDTO.getMeetingdetail());
		m.setMtEventType(me);
		tmMeetingRepository.save(m);
		meetingDTO.getParticipants().parallelStream().forEach(s ->
		tmMeetingMemberRepository.save(new MeetingMembers(m, s))
		);
		
	}

	public List<Meeting> getAllMeeting(){ 
		return tmMeetingRepository.findAll();
	}
	
	
	public void deleteEvent(Long eventid) {
		tmMeetingRepository.deleteById(eventid);

	}
	
	public Meeting findMeetingById(Long Id) { 
		return tmMeetingRepository.getOne(Id);
	}
	
	public void updateEvent(MeetingDTO meetingDTO) throws ParseException {
		Timestamp ts=new Timestamp(meetingDTO.getStart_date().getTime());  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        String sdate=formatter.format(ts)+" "+meetingDTO.getStart_time()+":00";
        SimpleDateFormat formatforsdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date strdate=formatforsdate.parse(sdate); 
		
     

       Timestamp te=new Timestamp(meetingDTO.getEnd_date().getTime());  
        SimpleDateFormat formattere = new SimpleDateFormat("yyyy-MM-dd");  
        String edate=formattere.format(te)+" "+meetingDTO.getEnd_time()+":00";
        SimpleDateFormat formatforedate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date enddate=formatforedate.parse(edate);
		
		
        tmMeetingRepository.updateEvent(meetingDTO.getMeetingId(), strdate, enddate, meetingDTO.getVenue(), meetingDTO.getMeetingdetail());
        
	}
	
	
	
}

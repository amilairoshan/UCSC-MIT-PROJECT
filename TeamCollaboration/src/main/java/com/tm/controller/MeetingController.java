package com.tm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tm.DTO.MeetingDTO;
import com.tm.DTO.ProjectDTO;
import com.tm.DTO.ResponseDTO;
import com.tm.DTO.UsersLogingInfoDTO2;
import com.tm.model.MTEventType;
import com.tm.model.Meeting;
import com.tm.model.Project;
import com.tm.model.UserDetail;
import com.tm.model.Users;
import com.tm.service.TmEvent;
import com.tm.service.TmMeetingService;
import com.tm.service.TmUserService;

@Controller
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
public class MeetingController {

	@Autowired
	private TmEvent tmEvent;
	
	@Autowired
	private TmUserService tmUserService;
	
	@Autowired
	private TmMeetingService tmMeetingService;
	
	private static final Logger LOGGER = LogManager.getLogger(MeetingController.class);	

	
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
	
	
	
	
	@RequestMapping(value={"/meetings"}, method = RequestMethod.GET)
	public ModelAndView postLogin(RedirectAttributes atts) {
		Map<String, Object> params = new HashMap();
		params.put("page", "Meetings");
		params.put("events", tmEvent.retreiveEvents());
		params.put("teammembers", tmUserService.getAllUsers());
		params.put("role", getUserLoginInfo().getUserrole());
		params.put("loginUser", getUserLoginInfo().getUsername());	
		if(getUserLoginInfo().getUserrole().equalsIgnoreCase("admin")) {
			
			return new ModelAndView("pages/home", params);	
		}else {
			
			return new ModelAndView("pages/userhome", params);
		}
		//return new ModelAndView("pages/home", params);
	}
	
	@RequestMapping(value = "/addEvent",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO performLogin(@RequestBody MTEventType ev, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmEvent.saveEvent(ev);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	@RequestMapping(value = "/meetingSave",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO performLogin(@RequestBody MeetingDTO meetingDTO, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmMeetingService.saveNewMeeting(meetingDTO);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	@RequestMapping(value = "/getEventForCalender",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO getEventForCalender( HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			List<Meeting> meetings=tmMeetingService.getAllMeeting();
			if(!meetings.isEmpty()) { 
				List<CalenderRepresentation> cevents = new ArrayList<CalenderRepresentation>();
				for(Meeting m:meetings) {
				CalenderRepresentation cr=new CalenderRepresentation();
				cr.setTitle(m.getMtEventType().getEvent_name());
				cr.setStart(m.getStart_date().toString());
				cr.setEnd(m.getEnd_date().toString());
				cr.setAllDay(false);
				cr.setBackgroundColor("#00a65a");
				cr.setBorderColor("#00a65a");
				cr.setVenue(m.getVenue());
				cevents.add(cr);
				}
				res.setData(cevents);
				res.setStatus(true);
			}
			else {
				res.setStatus(false);
			}
			
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	
	
	class CalenderRepresentation {
		
		public String title;
		public String start;
		public String end;
		public boolean allDay;
		public String backgroundColor;
		public String borderColor;
		public String venue;
		public String getVenue() {
			return venue;
		}
		public void setVenue(String venue) {
			this.venue = venue;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getStart() {
			return start;
		}
		public void setStart(String start) {
			this.start = start;
		}
		public String getEnd() {
			return end;
		}
		public void setEnd(String end) {
			this.end = end;
		}
		public boolean isAllDay() {
			return allDay;
		}
		public void setAllDay(boolean allDay) {
			this.allDay = allDay;
		}
		public String getBackgroundColor() {
			return backgroundColor;
		}
		public void setBackgroundColor(String backgroundColor) {
			this.backgroundColor = backgroundColor;
		}
		public String getBorderColor() {
			return borderColor;
		}
		public void setBorderColor(String borderColor) {
			this.borderColor = borderColor;
		}
		@Override
		public String toString() {
			return "CalenderRepresentation [title=" + title + ", start=" + start + ", end=" + end + ", allDay=" + allDay
					+ ", backgroundColor=" + backgroundColor + ", borderColor=" + borderColor + "]";
		}
		
		
		
		
		
		
		
	}
	
	@RequestMapping(value={"/editMeetings"}, method = RequestMethod.GET)
	public ModelAndView editMeetings(RedirectAttributes atts) {
	
		Map<String, Object> params = new HashMap();
		params.put("page", "EditMeetings");
		params.put("eventinfo", tmMeetingService.getAllMeeting());
		return new ModelAndView("pages/home", params);
	}
	
	@RequestMapping(value = "/eventDelete",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO eventDelete(@RequestBody Long eventId, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmMeetingService.deleteEvent(eventId);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		 return res;
	}	
	}
	
	@RequestMapping(value = "/eventView",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO projectView(@RequestBody Long eventId, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			MeetingDTO meetingdto=new MeetingDTO();
			Meeting m=tmMeetingService.findMeetingById(eventId);
			meetingdto.setMtEventType(m.getMtEventType().getEvent_name());
			meetingdto.setVenue(m.getVenue());
			meetingdto.setStart_date(m.getStart_date());
			
		    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		    String formattedtime= dateFormat.format(m.getStart_date());
			meetingdto.setStart_time(formattedtime);
			
			meetingdto.setEnd_date(m.getEnd_date());
			
			 DateFormat enddateFormat = new SimpleDateFormat("HH:mm");
			 String formattedendtime= enddateFormat.format(m.getEnd_date());
			 meetingdto.setEnd_time(formattedendtime);
			
			 meetingdto.setMeetingdetail(m.getMeetingdetail());
			
			
			res.setData(meetingdto);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		 return res;
	}	
	}
	
	
	
	
	@RequestMapping(value = "/eventUpdate",method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateEvent(@RequestBody MeetingDTO  meetingDTO, HttpServletRequest request, HttpServletResponse response) {
		ResponseDTO res=new ResponseDTO();
		try {
			tmMeetingService.updateEvent(meetingDTO);
			res.setStatus(true);
			return res;
	} catch (Exception e) {
		e.printStackTrace();
		res.setStatus(false);
		return res;
	}	
	}
	
	
	
}

package com.tm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.model.MTEventType;
import com.tm.repository.TmEventType;

@Service
public class TmEvent {
	
	@Autowired
	private TmEventType tmEventType;
	
	
	public void saveEvent(MTEventType ev) {
		tmEventType.save(ev);
	}
	
	public List<MTEventType> retreiveEvents(){
		return tmEventType.findAllEvents();
		
	}
	

}

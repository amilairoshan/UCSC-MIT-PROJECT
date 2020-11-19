  var calevent=[];
$(function(){
		        $('#meetingparticipants').multiSelect();
		        
		    	$.ajax({
			        type: "POST",
			        url: "/TeamCollaboration/getEventForCalender",
			        cache : false,
			        async : true,
			        headers: { 
			            'Accept': 'application/json',
			            'Content-Type': 'application/json' 
			        },
			        dataType: 'json',
			        success: function (data) {
			        	if(data.status){ 
			        		var a=[];
			        		$.each(data.data, function(index) {
			        			
			        			var x={
			        				 'title': data.data[index].title+' '+data.data[index].venue,
		        					  'start': data.data[index].start,
		        					  'end': data.data[index].end,
		        					  'allDay': data.data[index].allDay,
		        					  'backgroundColor': data.data[index].backgroundColor,
		        					  'borderColor': data.data[index].borderColor
		        					  };
			        			
			        			a.push(x);
			        	        });
			        		calevent.push(a);
			        		
			        		
			        		calenderInitFunction();
			        	}else{ 
			        	}
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			        	toastr.error(textStatus);

			        }
		
			  });	
		    	
		    	
		    
		        
		   /*   calevent.push([{
			    	  title          : 'Birthday Party',
			          start          : '2020-07-26 11:00',
			          end            : '2020-07-26 11:00',
			          allDay         : false,
			          backgroundColor: '#00a65a', //Success (green)
			          borderColor    : '#00a65a' //Success (green)
			    	    },
			    	    {
			    	      title          : 'Birthday Party',
			  	          start          : '2020-06-15T09:00:00',
			  	          end            : '2020-06-15T10:00:00',
			  	          allDay         : false,
			  	          backgroundColor: '#00a65a', //Success (green)
			  	          borderColor    : '#00a65a' //Success (green)
			        }]);
		  	*/
		      
		    });

//$(function () {
function calenderInitFunction() {

	    /* initialize the external events
	     -----------------------------------------------------------------*/
	    function ini_events(ele) {
	    	
	    	ele.each(function () {

	        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
	        // it doesn't need to have a start or end
	        var eventObject = {
	          title: $.trim($(this).text()) // use the element's text as the event title
	        }

	        // store the Event Object in the DOM element so we can get to it later
	        $(this).data('eventObject', eventObject)

	        // make the event draggable using jQuery UI
	        $(this).draggable({
	          zIndex        : 1070,
	          revert        : true, // will cause the event to go back to its
	          revertDuration: 0  //  original position after the drag
	        })

	      })
	    }

	    ini_events($('#external-events div.external-event'))

	    /* initialize the calendar
	     -----------------------------------------------------------------*/
	    //Date for the calendar events (dummy data)
	    var date = new Date()
	    var d    = date.getDate(),
	        m    = date.getMonth(),
	        y    = date.getFullYear()

	    var Calendar = FullCalendar.Calendar;
	    var Draggable = FullCalendarInteraction.Draggable;

	    var containerEl = document.getElementById('external-events');
	    var checkbox = document.getElementById('drop-remove');
	    var calendarEl = document.getElementById('calendar');

	   
	    
	    
	    // initialize the external events 
	    // -----------------------------------------------------------------

	    new Draggable(containerEl, {
	      itemSelector: '.external-event',
	      eventData: function(eventEl) {
	        console.log(eventEl);
	        return {
	          title: eventEl.innerText,
	          backgroundColor: window.getComputedStyle( eventEl ,null).getPropertyValue('background-color'),
	          borderColor: window.getComputedStyle( eventEl ,null).getPropertyValue('background-color'),
	          textColor: window.getComputedStyle( eventEl ,null).getPropertyValue('color'),
	        };
	      }
	    });

		var calendar = new Calendar(calendarEl, {
			
			plugins: [ 'bootstrap', 'interaction', 'dayGrid', 'timeGrid' ],
		      header    : {
		        left  : 'prev,next today',
		        center: 'title',
		        right : 'dayGridMonth,timeGridWeek,timeGridDay'
		      },
		      //Random default events
		      events    : calevent[0],
		      editable  : true,
		      droppable : true, // this allows things to be dropped onto the calendar !!!
		      drop      : function(info) {
		        // is the "remove after drop" checkbox checked?
		        if (checkbox.checked) {
		          // if so, remove the element from the "Draggable Events" list
		          info.draggedEl.parentNode.removeChild(info.draggedEl);
		        }
		      }    
		    });

	    calendar.render();
	     $('#calendar').fullCalendar()

	    /* ADDING EVENTS */
	    var currColor = '#3c8dbc' //Red by default
	    //Color chooser button
	    var colorChooser = $('#color-chooser-btn')
	    $('#color-chooser > li > a').click(function (e) {
	      e.preventDefault()
	      //Save color
	      currColor = $(this).css('color')
	      //Add color effect to button
	      $('#add-new-event').css({
	        'background-color': currColor,
	        'border-color'    : currColor
	      })
	    })
	    $('#add-new-event').click(function (e) {
	      e.preventDefault()
	      //Get value and make sure it is not null
	      var val = $('#new-event').val()
	      if (val.length == 0) {
	        return
	      }

	      //Create events
	      var event = $('<div />')
	      event.css({
	        'background-color': currColor,
	        'border-color'    : currColor,
	        'color'           : '#fff'
	      }).addClass('external-event')
	      event.html(val)
	      $('#external-events').prepend(event)

	      //Add draggable funtionality
	      ini_events(event)

	      //Remove event from text input
	      $('#new-event').val('')
	    })
}
	
	
	//Add new Event
	function addEvent(){
	  	var event_name=$("#eventname").val();
	
		if(event_name=='' ){ 
			toastr.error('Please Fill Valid Value To Required Fields. ');
		}
		else{ 
			toastr.success('Successfuly Added New Event');
			var data= {
					event_name:event_name,
					}
					$.ajax({
					        type: "POST",
					        url: "/TeamCollaboration/addEvent",
					        cache : false,
					        async : true,
					        headers: { 
					            'Accept': 'application/json',
					            'Content-Type': 'application/json' 
					        },
					        data: JSON.stringify(data),
					        dataType: 'json',
					        success: function (data) {
					        	if(data.status =='true'){ 
					        		$('#closeevent').click();
						        	 $("div").removeClass("modal-backdrop fade show");
					        		window.location.href = '/TeamCollaboration/meetings'; 
					        	}else{ 
					        		window.location.href = '/TeamCollaboration/meetings'; 	
					        	}
					        	
					        	
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					        	toastr.error(textStatus);

					        }
				
					  });	
			
			
		}
	
	
	}
/*	SHOW MEETING POPUP*/	
	function createMeeting(eventType){ 
		$('#eventtypeid').val(eventType);
		$('#addNewMeeting').modal('show');
	
	}

	/*SAVE MEETING*/
	$('#savemeeting').on("click",function() {
		
		var mtEventType=$("#eventtypeid").val();
		var venue=$("#meetingvenue").val();
		var start_date=$("#meetingstartdate").val();
		var start_time=$("#meetingstarttime").val();
		var end_date=$("#meetingenddate").val();
		var end_time=$("#meetingendtime").val();
		var meetingdetail=$("#meetingdetails").val();
		var participants=$("#meetingparticipants").val();
	
		if(venue=='' || start_date=='' || start_time==''|| end_date=='' || end_time=='' || participants=='' ){ 
			toastr.error('Please Fill Valid Value To Required Fields. ');
		}
		else{ 
			toastr.success('Successfuly Added New Meeting');
			var data= {
					mtEventType: mtEventType,
					venue: venue,
					staus: projectstatus,
					start_date: start_date,
					start_time: start_time,
					end_date:end_date,
					end_time: end_time,
					participants: participants,
					meetingdetail: meetingdetail,
					}
					$.ajax({
					        type: "POST",
					        url: "/TeamCollaboration/meetingSave",
					        cache : false,
					        async : true,
					        headers: { 
					            'Accept': 'application/json',
					            'Content-Type': 'application/json' 
					        },
					        data: JSON.stringify(data),
					        dataType: 'json',
					        success: function (data) {
					        	if(data.status =='true'){ 
					        		$('#closemeeting').click();
						        	 $("div").removeClass("modal-backdrop fade show");
					        		window.location.href = '/TeamCollaboration/meetings'; 
					        	}else{ 
					        		window.location.href = '/TeamCollaboration/meetings'; 	
					        	}
					        	
					        	
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					        	toastr.error(textStatus);

					        }
				
					  });	
			
			
		}
	
	});

	 function eventDelete(id){
			toastr.success('Successfuly Deleted');
			$.ajax({
		        type: "POST",
		        url: "/TeamCollaboration/eventDelete",
		        cache : false,
		        async : true,
		        headers: { 
		            'Accept': 'application/json',
		            'Content-Type': 'application/json' 
		        },
		        data: JSON.stringify(id),
		        dataType: 'json',
		        success: function (data) {
		        		window.location.href = '/TeamCollaboration/editMeetings'; 
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		        	toastr.error(errorThrown);

		        }

		  });	
		    }

	 function eventEdit(id){
			
			$.ajax({
		        type: "POST",
		        url: "/TeamCollaboration/eventView",
		        cache : false,
		        async : true,
		        headers: { 
		            'Accept': 'application/json',
		            'Content-Type': 'application/json' 
		        },
		        data: JSON.stringify(id),
		        dataType: 'json',
		        success: function (data) {
		        	var st=data.status;
		        	var dt=data.data;
		        	if(st){ 
		        		
		        		$('#eventid').val(id);
		        		$('#editmeetingvenue').val(dt.venue);
		        		$('#editmeetingstartdate').val(dt.start_date);
		        		$('#editmeetingstarttime').val(dt.start_time);
		        		$('#editmeetingenddate').val(dt.end_date);
		        		$('#editmeetingendtime').val(dt.end_time);
		        		$("#editmeetingdetails").val(dt.meetingdetail);
		        		
		        		$('#editMeetingModal').modal('show');
		        		
		        	}else{ 
		        		toastr.error("Error Ocurred");
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		        	toastr.error(errorThrown);

		        }

		  });	
		
	    }
	
	  function updateEvent(){
			var meetingId=$("#eventid").val();
		  	var venue=$("#editmeetingvenue").val();
			var start_date=$("#editmeetingstartdate").val();
			var start_time=$("#editmeetingstarttime").val();
			var end_date=$("#editmeetingenddate").val();
			var end_time=$("#editmeetingendtime").val();
			var meetingdetail=$("#editmeetingdetails").val();

			if(venue=='' || start_date=='' || start_time=='' || end_date=='' || end_time==''  ){ 
				toastr.error('Please Fill Valid Value To Required Fields. ');
			}
			else{ 
				toastr.success('Successfuly Update Project');
				var data= {
						meetingId:meetingId,
						venue: venue,
						start_date: start_date,
						start_time: start_time,
						end_date: end_date,
						end_time: end_time,
						meetingdetail: meetingdetail,
						
						}
						$.ajax({
						        type: "POST",
						        url: "/TeamCollaboration/eventUpdate",
						        cache : false,
						        async : true,
						        headers: { 
						            'Accept': 'application/json',
						            'Content-Type': 'application/json' 
						        },
						        data: JSON.stringify(data),
						        dataType: 'json',
						        success: function (data) {
						        	if(data.status =='true'){ 
						        		$('#closeevent').click();
							        	 $("div").removeClass("modal-backdrop fade show");
						        		window.location.href = '/TeamCollaboration/editMeetings'; 
						        	}else{ 
						        		window.location.href = '/TeamCollaboration/editMeetings'; 	
						        	}
						        	
						        	
						        },
						        error: function (jqXHR, textStatus, errorThrown) {
						        	toastr.error(textStatus);

						        }
					
						  });	
				
				
			}
		
		
		}
	 
	 
	 
	 
$(function(){
	bsCustomFileInput.init();
	    $('.textarea').summernote({
	    	  minHeight: 200,
	    	  placeholder: 'Write here ...',
	    	  focus: false,
	    	  airMode: false,
	    	  fontNames: ['Roboto', 'Calibri', 'Times New Roman', 'Arial'],
	    	  fontNamesIgnoreCheck: ['Roboto', 'Calibri'],
	    	  dialogsInBody: true,
	    	  dialogsFade: true,
	    	  disableDragAndDrop: false,
	    	  toolbar: [
	    	    // [groupName, [list of button]]
	    	    ['style', ['bold', 'italic', 'underline', 'clear']],
	    	    ['para', ['style', 'ul', 'ol', 'paragraph']],
	    	    ['fontsize', ['fontsize']],
	    	    ['font', ['strikethrough', 'superscript', 'subscript']],
	    	    ['height', ['height']],
	    	    ['misc', ['undo', 'redo', 'print', 'fullscreen']],
	    	   

	    	  ],
	    	  popover: {
	    	    air: [
	    	      ['color', ['color']],
	    	      ['font', ['bold', 'underline', 'clear']]
	    	    ]
	    	  },
  
	    	});
	
	    $('.textareadefect').summernote({
	    	  minHeight: 200,
	    	  placeholder: 'Write here ...',
	    	  focus: false,
	    	  airMode: false,
	    	  fontNames: ['Roboto', 'Calibri', 'Times New Roman', 'Arial'],
	    	  fontNamesIgnoreCheck: ['Roboto', 'Calibri'],
	    	  dialogsInBody: true,
	    	  dialogsFade: true,
	    	  disableDragAndDrop: false,
	    	  toolbar: [
	    	    // [groupName, [list of button]]
	    	    ['style', ['bold', 'italic', 'underline', 'clear']],
	    	    ['para', ['style', 'ul', 'ol', 'paragraph']],
	    	    ['fontsize', ['fontsize']],
	    	    ['font', ['strikethrough', 'superscript', 'subscript']],
	    	    ['height', ['height']],
	    	    ['misc', ['undo', 'redo', 'print', 'fullscreen']],
	    	   

	    	  ],
	    	  popover: {
	    	    air: [
	    	      ['color', ['color']],
	    	      ['font', ['bold', 'underline', 'clear']]
	    	    ]
	    	  },

	    	});
	    
	    
	    
	    
		    });

/*SAVE CR*/
$('#savenewcr').on("click",function() {
	 var taskReporter=[];
	 var taskAssignee=[];
	var task_status='1';
	var project=$("#projectnamecr").val();
	var task_type='Change Request';
	var task_severity=$("#severitycr").val();
	var task_prority=$("#prioritycr").val();
	var summary='';
	var description=$(".textarea").val();
	var due_date=$("#duedatecr").val();
	taskReporter.push($("#reportercr").val());
	taskAssignee.push($("#assigneecr").val());
	
	

	if(task_status=='' || project=='' || task_severity==''|| task_prority=='' || due_date=='' || taskReporter==''|| taskAssignee==''){ 
		toastr.error('Please Fill Valid Value To Required Fields. ');
	}
	else{ 
		toastr.success('Successfuly Added New Change Request');
		var data= {
				task_status: task_status,
				project: project,
				task_type: task_type,
				task_severity: task_severity,
				task_prority: task_prority,
				description:description,
				due_date: due_date,
				taskReporter: taskReporter,
				taskAssignee: taskAssignee,
				

				}
		
		
				$.ajax({
				       
					type: "POST",
				        enctype: 'multipart/form-data',
				        url: "/TeamCollaboration/changeRequestSave",
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
				        		$('#closecr').click();
					        	 $("div").removeClass("modal-backdrop fade show");
				        		window.location.href = '/TeamCollaboration/ChangeRequest'; 
				        	}else{ 
				        		window.location.href = '/TeamCollaboration/ChangeRequest'; 	
				        	}
				        	
				        	
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				        	toastr.error(textStatus);

				        }
			
				  });	
		
		
	}

});
	

function taskDelete(id){
	toastr.success('Successfuly Deleted');
	$.ajax({
        type: "POST",
        url: "/TeamCollaboration/taskDelete",
        cache : false,
        async : true,
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        data: JSON.stringify(id),
        dataType: 'json',
        success: function (data) {
        		window.location.href = '/TeamCollaboration/ChangeRequest'; 
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	toastr.error(errorThrown);

        }

  });	
    }

function taskEdit(id){
	
	$.ajax({
        type: "POST",
        url: "/TeamCollaboration/taskView",
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
        		
        		$('#edittaskid').val(id);
        		$('#editprojectnamecr').val(dt.project);
        		$('#edittasktypecr').val('Change Request');
        		$('#editseveritycr').val(dt.task_severity);
        		$('#editprioritycr').val(dt.task_prority);
        		$('#editreportercr').val(dt.taskReporter[0]);
        		$("#editassigneecr").val(dt.taskAssignee[0]);
        		$("#editduedatecr").val(dt.due_date);
        		$("#editstatuscr").val(dt.task_status);
        		
        		$('#editEditor').summernote('code',dt.description);
        		$('#editTaskModal').modal('show');
        		
        	}else{ 
        		toastr.error("Error Ocurred");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	toastr.error(errorThrown);

        }

  });	


}
function updateTask(){
	
	var taskReporter=[];
	var taskAssignee=[];
	var taskId=$('#edittaskid').val();
	var project=$("#editprojectnamecr").val();
	var task_type='Change Request';
	var task_severity=$("#editseveritycr").val();
	var task_prority=$("#editprioritycr").val();
	var summary='';
	var description=$('#editEditor').val();
	var due_date=$("#editduedatecr").val();
	var task_status=$("#editstatuscr").val();
	taskReporter.push($("#editreportercr").val());
	taskAssignee.push($("#editassigneecr").val());
	

	if(task_status=='' || project=='' || task_severity==''|| task_prority=='' || due_date=='' || taskReporter==''|| taskAssignee==''){ 
		toastr.error('Please Fill Valid Value To Required Fields. ');
	}
	else{ 
		toastr.success('Successfuly Update Change Request');
		var data= {
				taskId: taskId,
				task_status: task_status,
				project: project,
				task_type: task_type,
				task_severity: task_severity,
				task_prority: task_prority,
				description:description,
				due_date: due_date,
				taskReporter: taskReporter,
				taskAssignee: taskAssignee,
				}
				$.ajax({
				       
					type: "POST",
				        enctype: 'multipart/form-data',
				        url: "/TeamCollaboration/changeRequestUpdate",
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
				        		$('#closeeditcr').click();
					        	 $("div").removeClass("modal-backdrop fade show");
				        		window.location.href = '/TeamCollaboration/ChangeRequest'; 
				        	}else{ 
				        		window.location.href = '/TeamCollaboration/ChangeRequest'; 	
				        	}
				        	
				        	
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				        	toastr.error(textStatus);

				        }
			
				  });	
		
		
	}

}

////////////////////////////////////////// Defects /////////////////////////////////////////////////////////////

/*SAVE DEFECT*/
$('#savenewdefect').on("click",function() {
	 var taskReporter=[];
	 var taskAssignee=[];
	var task_status='1';
	var project=$("#projectnamedefect").val();
	var task_type='Defect/Bug';
	var task_severity=$("#severitydefect").val();
	var task_prority=$("#prioritydefect").val();
	var summary='';
	var description=$(".textareadefect").val();
	var due_date=$("#duedatedefect").val();
	taskReporter.push($("#reporterdefect").val());
	taskAssignee.push($("#assigneedefect").val());
	
	

	if(task_status=='' || project=='' || task_severity==''|| task_prority=='' || due_date=='' || taskReporter==''|| taskAssignee==''){ 
		toastr.error('Please Fill Valid Value To Required Fields. ');
	}
	else{ 
		toastr.success('Successfuly Added New Defect');
		var data= {
				task_status: task_status,
				project: project,
				task_type: task_type,
				task_severity: task_severity,
				task_prority: task_prority,
				description:description,
				due_date: due_date,
				taskReporter: taskReporter,
				taskAssignee: taskAssignee,
			}
				$.ajax({
				       
					type: "POST",
				        enctype: 'multipart/form-data',
				        url: "/TeamCollaboration/defectSave",
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
				        		$('#closedefect').click();
					        	 $("div").removeClass("modal-backdrop fade show");
				        		window.location.href = '/TeamCollaboration/Defect'; 
				        	}else{ 
				        		window.location.href = '/TeamCollaboration/Defect'; 	
				        	}
				        	
				        	
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				        	toastr.error(textStatus);

				        }
			
				  });	
		
		
	}

});

function defectDelete(id){
	toastr.success('Successfuly Deleted');
	$.ajax({
        type: "POST",
        url: "/TeamCollaboration/taskDelete",
        cache : false,
        async : true,
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        data: JSON.stringify(id),
        dataType: 'json',
        success: function (data) {
        		window.location.href = '/TeamCollaboration/Defect'; 
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	toastr.error(errorThrown);

        }

  });	
    }
	

function defectEdit(id){
	
	$.ajax({
        type: "POST",
        url: "/TeamCollaboration/taskView",
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
        		
        		$('#editdefectid').val(id);
        		$('#editprojectnamedefect').val(dt.project);
        		$('#edittasktypedefect').val('Defect/Bug');
        		$('#editseveritydefect').val(dt.task_severity);
        		$('#editprioritydefect').val(dt.task_prority);
        		$('#editreporterdefect').val(dt.taskReporter[0]);
        		$("#editassigneedefect").val(dt.taskAssignee[0]);
        		$("#editduedatedefect").val(dt.due_date);
        		$("#editstatusdefect").val(dt.task_status);

        		
        		$('#editdefectEditor').summernote('code',dt.description);
        		$('#editDefectModal').modal('show');
        		
        	}else{ 
        		toastr.error("Error Ocurred");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	toastr.error(errorThrown);

        }

  });	


}

function updateDefectTask(){
	
	var taskReporter=[];
	var taskAssignee=[];
	var taskId=$('#editdefectid').val();
	var project=$("#editprojectnamedefect").val();
	var task_type='Defect/Bug';
	var task_severity=$("#editseveritydefect").val();
	var task_prority=$("#editprioritydefect").val();
	var summary='';
	var description=$('#editdefectEditor').val();
	var due_date=$("#editduedatedefect").val();
	var task_status=$("#editstatusdefect").val();
	taskReporter.push($("#editreporterdefect").val());
	taskAssignee.push($("#editassigneedefect").val());
	

	if(task_status=='' || project=='' || task_severity==''|| task_prority=='' || due_date=='' || taskReporter==''|| taskAssignee==''){ 
		toastr.error('Please Fill Valid Value To Required Fields. ');
	}
	else{ 
		toastr.success('Successfuly Update Defect');
		var data= {
				taskId: taskId,
				task_status: task_status,
				project: project,
				task_type: task_type,
				task_severity: task_severity,
				task_prority: task_prority,
				description:description,
				due_date: due_date,
				taskReporter: taskReporter,
				taskAssignee: taskAssignee,
				}
				$.ajax({
				       
					type: "POST",
				        enctype: 'multipart/form-data',
				        url: "/TeamCollaboration/defectUpdate",
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
				        		$('#closeeditdefect').click();
					        	 $("div").removeClass("modal-backdrop fade show");
				        		window.location.href = '/TeamCollaboration/Defect'; 
				        	}else{ 
				        		window.location.href = '/TeamCollaboration/Defect'; 	
				        	}
				        	
				        	
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				        	toastr.error(textStatus);

				        }
			
				  });	
		
		
	}

}

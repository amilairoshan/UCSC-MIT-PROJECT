$(document).ready(function () {
	$('#datepicker').datepicker();
		

	$('#saveproject').on("click",function() {
		
		var projectname=$("#projectname").val();
		var projectstatus=$("#projectstatus").val();
		var projectkey=$("#projectkey").val();
		var projectlifecycle=$("#projectlifecycle").val();
		var projectstartdate=$("#projectstartdate").val();
		var projectenddate=$("#projectenddate").val();
		var projectmenager=$("#projectmenager").val();
		var projectlead=$("#projectlead").val();
		var teammenbr=$("#teammenbr").val();
		var projectdetails=$("#projectdetails").val();

		if(projectname=='' || projectstatus=='' || projectkey=='' || projectlifecycle=='' || projectstartdate=='' || projectenddate=='' ){ 
			toastr.error('Please Fill Valid Value To Required Fields. ');
		}
		else{ 
			toastr.success('Successfuly Added New Project');
			var data= {
					projectname: projectname,
					projectkey: projectkey,
					staus: projectstatus,
					lifecycle: projectlifecycle,
					start_date: projectstartdate,
					end_date: projectenddate,
					projectManagers: projectmenager,
					projectLeads: projectlead,
					projectTeam: teammenbr,
					projectdetail: projectdetails,
					}
					$.ajax({
					        type: "POST",
					        url: "/TeamCollaboration/projectSave",
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
					        		$('#closeproject').click();
						        	 $("div").removeClass("modal-backdrop fade show");
					        		window.location.href = '/TeamCollaboration/home'; 
					        	}else{ 
					        		window.location.href = '/TeamCollaboration/home'; 	
					        	}
					        	
					        	
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					        	toastr.error(textStatus);

					        }
				
					  });	
			
			
		}
	
	});




});
			


		  $(function(){
		        $('#projectmenager').multiSelect();
		        $('#teammenbr').multiSelect();
		        $('#projectlead').multiSelect();
		        $('#editprojectmenager').multiSelect();
		        $('#editteammenbr').multiSelect();
		        $('#editprojectlead').multiSelect();
		        
		    });
	
		  
		  
		  
		  function projectView(id){
			  $('#viewprojectlifecycle').attr("disabled", true); 
			  $('#viewprojectstatus').attr("disabled", true); 
				$.ajax({
			        type: "POST",
			        url: "/TeamCollaboration/projectView",
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
			        		$('#viewprojectname').val(dt.projectname);
			        		$('#viewprojectstatus').val(dt.staus);
			        		$('#viewprojectkey').val(dt.projectkey);
			        		$('#viewprojectlifecycle').val(dt.lifecycle);
			        		$('#viewprojectstartdate').val(dt.start_date);
			        		$('#viewprojectenddate').val(dt.end_date);
			        		$("#viewprojectmenager").val(dt.projectManagersDetails);
			        		$("#viewprojectlead").val(dt.projectLeadsDetails);
			        		$("#viewteammenbr").val(dt.projectTeamDetails);
			        		$("#viewprojectdetails").val(dt.projectdetail);
			        		
			        		$('#viewProjectModal').modal('show');
			        		
			        	}else{ 
			        		toastr.error("Error Ocurred");
			        	}
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			        	toastr.error(errorThrown);

			        }

			  });	
			}
	
		  function projectDelete(id){
				toastr.success('Successfuly Deleted');
				$.ajax({
			        type: "POST",
			        url: "/TeamCollaboration/projectDelete",
			        cache : false,
			        async : true,
			        headers: { 
			            'Accept': 'application/json',
			            'Content-Type': 'application/json' 
			        },
			        data: JSON.stringify(id),
			        dataType: 'json',
			        success: function (data) {
			        		window.location.href = '/TeamCollaboration/home'; 
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			        	toastr.error(errorThrown);

			        }

			  });	
			    }
	
		  function projectEdit(id){
				
				$.ajax({
			        type: "POST",
			        url: "/TeamCollaboration/projectView",
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
			        		
			        		$('#projectid').val(id);
			        		$('#editprojectname').val(dt.projectname);
			        		$('#editprojectstatus').val(dt.staus);
			        		$('#editprojectkey').val(dt.projectkey);
			        		$('#editprojectlifecycle').val(dt.lifecycle);
			        		$('#editprojectstartdate').val(dt.start_date);
			        		$('#editprojectenddate').val(dt.end_date);
			        		$("#editprojectmenager").val(dt.projectManagersDetails);
			        		$("#editprojectlead").val(dt.projectLeadsDetails);
			        		$("#editteammenbr").val(dt.projectTeamDetails);
			        		$("#editprojectdetails").val(dt.projectdetail);
			        		
			        		$('#editProjectModal').modal('show');
			        		
			        	}else{ 
			        		toastr.error("Error Ocurred");
			        	}
			        },
			        error: function (jqXHR, textStatus, errorThrown) {
			        	toastr.error(errorThrown);

			        }

			  });	
			
		    }
		  

		  function updateProject(){
			  	var projectId=$("#projectid").val();
			  	var projectname=$("#editprojectname").val();
				var projectstatus=$("#editprojectstatus").val();
				var projectkey=$("#editprojectkey").val();
				var projectlifecycle=$("#editprojectlifecycle").val();
				var projectstartdate=$("#editprojectstartdate").val();
				var projectenddate=$("#editprojectenddate").val();
				var projectmenager=$("#editprojectmenager").val();
				var projectlead=$("#editprojectlead").val();
				var teammenbr=$("#editteammenbr").val();
				var projectdetails=$("#editprojectdetails").val();

				if(projectname=='' || projectstatus=='' || projectkey=='' || projectlifecycle=='' || projectstartdate=='' || projectenddate=='' ){ 
					toastr.error('Please Fill Valid Value To Required Fields. ');
				}
				else{ 
					toastr.success('Successfuly Update Project');
					var data= {
							projectId:projectId,
							projectname: projectname,
							projectkey: projectkey,
							staus: projectstatus,
							lifecycle: projectlifecycle,
							start_date: projectstartdate,
							end_date: projectenddate,
							projectManagers: projectmenager,
							projectLeads: projectlead,
							projectTeam: teammenbr,
							projectdetail: projectdetails,
							}
							$.ajax({
							        type: "POST",
							        url: "/TeamCollaboration/updateProject",
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
							        		$('#closeproject').click();
								        	 $("div").removeClass("modal-backdrop fade show");
							        		window.location.href = '/TeamCollaboration/home'; 
							        	}else{ 
							        		window.location.href = '/TeamCollaboration/home'; 	
							        	}
							        	
							        	
							        },
							        error: function (jqXHR, textStatus, errorThrown) {
							        	toastr.error(textStatus);

							        }
						
							  });	
					
					
				}
			
			
			}
		
	
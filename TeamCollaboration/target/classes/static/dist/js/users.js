
$(document).ready(function () {
	
	
			$('#saveusers').on("click",function() {
				var fullName=$("#fullname").val();
				var nic=$("#nic").val();
				var tpNumber=$("#tpnumber").val();
				var userRole1=$("#adduserrole").val();
				/*if($("#customCheckbox1").prop('checked') == true){
					var userRole1=true;
				}
				if($("#customCheckbox2").prop('checked') == true){
					var userRole2=true;
				}
				|| (userRole1!=true && userRole2!=true)*/
				var address=$("#address").val();
				var email=$("#email").val();
				var userName=$("#username").val();
				var password=$("#password").val();
				var designation=$("#designation").val();
				var detail=$("#details").val();
				if(fullName=='' || nic=='' || tpNumber=='' || email=='' || userName=='' || password=='' || designation=='' || userRole1=='' ){ 
					toastr.error('Please Fill Valid Value To Required Fields. ');
				}
				else{ 
					
					var data= {fullName: fullName,
							nic: nic,
							tpNumber: tpNumber,
							userRole1: userRole1,
							//userRole2: userRole2,
							address: address,
							email: email,
							userName: userName,
							password: password,
							designation: designation,
							detail:detail,
							}
							$.ajax({
							        type: "POST",
							        url: "/TeamCollaboration/usersSave",
							        cache : false,
							        async : true,
							        headers: { 
							            'Accept': 'application/json',
							            'Content-Type': 'application/json' 
							        },
							        data: JSON.stringify(data),
							        dataType: 'json',
							        success: function (data) {
							        	var st=data.status;
							        	if(st){ 
							        		toastr.success('Successfuly Added New User');
							        		$('#addUserModal').hide();
								        	 $("div").removeClass("modal-backdrop fade show");
							        		window.location.href = '/TeamCollaboration/users'; 
							        	}else{ 
							        		toastr.error('User Name Already Exists !!');
							        		//window.location.href = '/TeamCollaboration/users'; 	
							        	}
							        	
							        	
							        },
							        error: function (jqXHR, textStatus, errorThrown) {
							        	toastr.error(errorThrown);

							        }
						
							  });	
					
					
				}
			
			});
			 $('#example1').DataTable({
			      "paging": true,
			      "lengthChange": false,
			      "searching": false,
			      "ordering": true,
			      "info": true,
			      "autoWidth": false,
			    });
			
			});

	function userDelete(id){
		toastr.success('Successfuly Deleted');
		$.ajax({
	        type: "POST",
	        url: "/TeamCollaboration/userDelete",
	        cache : false,
	        async : true,
	        headers: { 
	            'Accept': 'application/json',
	            'Content-Type': 'application/json' 
	        },
	        data: JSON.stringify(id),
	        dataType: 'json',
	        success: function (data) {
	        		window.location.href = '/TeamCollaboration/users'; 
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	        	toastr.error(errorThrown);

	        }

	  });	
	    }
	
	function userView(id){
		$("#enableuserpassword").css("visibility", "hidden");
		$("#usupdateButton").css("visibility", "hidden");
		$("#usviewButton").css("visibility", "visible");
		//$('#adminck').attr('checked', false);
		//$('#userck').attr('checked', false);
		$("#viewuser :input").prop("disabled", true);
		$.ajax({
	        type: "POST",
	        url: "/TeamCollaboration/userView",
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
	        		$('#viewfullname').val(dt.fullName);
	        		$('#viewnic').val(dt.nic);
	        		$('#viewtpnumber').val(dt.tpNumber);
	        		$('#viewuserrole').val(dt.userRole1);
	        		/*if(dt.userRole1 =='true'){
	        			$('#adminck').attr('checked', true);
	        		}
	        		if(dt.userRole2 =='true'){
	        			$('#userck').attr('checked', true);
	        		}*/
	        		$('#viewaddress').val(dt.address);
	        		$('#viewemail').val(dt.email);
	        		$('#viewusername').val(dt.userName);
	        		$('#viewpassword').val(dt.password);
	        		$('#viewdesignation').val(dt.designation);
	        		$('#viewdetails').val(dt.detail);	
	        		$('#viewUserModal').modal('show');
	        		
	        	}else{ 
	        		toastr.error("Error Ocurred");
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	        	toastr.error(errorThrown);

	        }

	  });	
	}
		
		function userEdit(id){
			$("#usviewButton").css("visibility", "hidden");
			$("#usupdateButton").css("visibility", "visible");
			//$('#adminck').attr('checked', false);
			//$('#userck').attr('checked', false);
			$("#viewuser :input").prop("disabled", false);
			$("#enableuserpassword").css("visibility", "hidden");
			$("#userid").val(id);
			$.ajax({
		        type: "POST",
		        url: "/TeamCollaboration/userView",
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
		        		$('#viewfullname').val(dt.fullName);
		        		$('#viewnic').val(dt.nic);
		        		$('#viewtpnumber').val(dt.tpNumber);
		        		$('#viewuserrole').val(dt.userRole1);
		        		
		        	/*	if(dt.userRole1 =='true'){
		        			$('#adminck').attr('checked', true);
		        		}
		        		if(dt.userRole2 =='true'){
		        			$('#userck').attr('checked', true);
		        		}*/
		        		$('#viewaddress').val(dt.address);
		        		$('#viewemail').val(dt.email);
		        		$('#viewusername').val(dt.userName);
		        		//$('#viewpassword').val(dt.password);
		        		$('#viewdesignation').val(dt.designation);
		        		$('#viewdetails').val(dt.detail);	
		        		$('#viewUserModal').modal('show');
		        		
		        	}else{ 
		        		toastr.error("Error Ocurred");
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		        	toastr.error(errorThrown);

		        }

		  });	
		
	    }
	
	
		function updateUser(){
			var userid=$("#userid").val();
			var fullName=$("#viewfullname").val();
			var nic=$("#viewnic").val();
			var tpNumber=$("#viewtpnumber").val();
			var userRole1=$("#viewuserrole").val();
			/*if($("#adminck").prop('checked') == true){
				var userRole1=true;
			}
			if($("#userck").prop('checked') == true){
				var userRole2=true;
			}*/
			var address=$("#viewaddress").val();
			var email=$("#viewemail").val();
			var userName=$("#viewusername").val();
			//var password=$("#viewuserpassword").val();
			var designation=$("#viewdesignation").val();
			var detail=$("#viewdetails").val();
			if(fullName=='' || nic=='' || tpNumber=='' || email=='' || userName=='' ||  designation=='' || userRole1==''  ){ 
				toastr.error('Please Fill Valid Value To Required Fields. ');
			}
			else{ 
				toastr.success('Successfuly Updated Existing User');
				var data= {userId: userid,
						fullName: fullName,
						nic: nic,
						tpNumber: tpNumber,
						userRole1: userRole1,
						//userRole2: userRole2,
						address: address,
						email: email,
						userName: userName,
						//password: password,
						designation: designation,
						detail:detail,
						}
						$.ajax({
						        type: "POST",
						        url: "/TeamCollaboration/updateUser",
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
						        		window.location.href = '/TeamCollaboration/users'; 
						        	}else{ 
						        		window.location.href = '/TeamCollaboration/users'; 	
						        	}
						        	
						        	
						        },
						        error: function (jqXHR, textStatus, errorThrown) {
						        	toastr.error(errorThrown);

						        }
					
						  });	
				
				
			}
		
		}
	
		function updateUserProfile(id){
			var userid=id;
			var fullName=$("#profilefullname").val();
			var nic=$("#profilenic").val();
			var tpNumber=$("#profiletp").val();
			
			var address=$("#profileaddress").val();
			var email=$("#profileemail").val();
			var userName=$("#profileusername").val();
			var designation=$("#profiledesignation").val();
			var detail=$("#profiledetails").val();
			if(fullName=='' || nic=='' || tpNumber=='' || email=='' || userName=='' ||  designation==''   ){ 
				toastr.error('Please Fill Valid Value To Required Fields. ');
			}
			else{ 
				toastr.success('Successfuly Updated Existing User');
				var data= {userId: userid,
						fullName: fullName,
						nic: nic,
						tpNumber: tpNumber,
						address: address,
						email: email,
						userName: userName,
						designation: designation,
						detail:detail,
						}
						$.ajax({
						        type: "POST",
						        url: "/TeamCollaboration/updateUserProfile",
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
						        		window.location.href = '/TeamCollaboration/myProfile'; 
						        	}else{ 
						        		window.location.href = '/TeamCollaboration/myProfile'; 	
						        	}
						        	
						        	
						        },
						        error: function (jqXHR, textStatus, errorThrown) {
						        	toastr.error(errorThrown);

						        }
					
						  });	
				
				
			}
		
		}
	
		function changeUserPassword(id){
			var userid=id;
			var newPassword=$("#changenewpassword").val();
			var confirmPassword=$("#confirmnewpassword").val();
		
			if(newPassword=='' || confirmPassword=='' ){ 
				toastr.error('Please Fill Valid Value To Required Fields. ');
			}
			if(newPassword!=confirmPassword ){ 
				toastr.error('The New password and confirmation password do not match');
			}
			
			else{ 
				toastr.success('Successfuly Updated User Password');
				var data= {userId: userid,
						newPassword: newPassword,
						confirmPassword: confirmPassword,
					
						}
						$.ajax({
						        type: "POST",
						        url: "/TeamCollaboration/updateUserPassword",
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
						        		window.location.href = '/TeamCollaboration/myProfile'; 
						        	}else{ 
						        		window.location.href = '/TeamCollaboration/myProfile'; 	
						        	}
						        	
						        	
						        },
						        error: function (jqXHR, textStatus, errorThrown) {
						        	toastr.error(errorThrown);

						        }
					
						  });	
				
				
			}
		
		}
		
		
	
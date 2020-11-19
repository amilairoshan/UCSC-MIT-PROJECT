$('#saveusers').on("click",function() {
	var fullName=$("#fullname").val();
	var nic=$("#nic").val();
	var tpNumber=$("#tpnumber").val();
	var userRole1=$("#Checkbox1").val();
	var userRole2=$("#Checkbox2").val();
	var address=$("#address").val();
	var email=$("#email").val();
	var userName=$("#username").val();
	var password=$("#password").val();
	var designation=$("#designation").val();
	var profPic=new FormData($("#inputFile").val());

	console.log(profPic);
	
	data: {fullName: fullName,
		nic: nic,
		tpNumber: tpNumber,
		userRole1: userRole1,
		userRole2: userRole2,
		address: address,
		email: email,
		userName: userName,
		password: password,
		designation: designation,
		profPic: profPic 
		},
		 $.ajax({
		        type: "POST",
		        enctype: 'multipart/form-data',
		        url: "/TeamCollaboration/usersSave",
		        data: data,
		        processData: false, 
		        contentType: false,
		        cache: false,
		        timeout: 600000,
		        success: function (data) {
		        	alert("success");
		        },
		        error: function (e) {
		        	alert("fail");

		        }
	
		  });
  
}); 

/*SAVE CR*/
$('#btnExport').on("click",function() {
	 html2canvas($('#dvTable')[0], {
         onrendered: function (canvas) {
             var data = canvas.toDataURL();
             var docDefinition = {
                 content: [{
                     image: data,
                     width: 500
                 }]
             };
             pdfMake.createPdf(docDefinition).download("Table.pdf");
         }
     });

});
	

$('#searchResult').on("click",function() {
	
	var project=$("#searchproject").val();
	var priority=$("#searchpriority").val();
	var severity=$("#searchseverity").val();
	var status=$("#searchstatus").val();
	var tasktype=$("#searchtasktype").val();
	
	 var data= {project: project,
			 	priority: priority,
			 	severity: severity,
			 	status: status,
			 	tasktype: tasktype,
			
				}
				$.ajax({
				        type: "POST",
				        url: "/TeamCollaboration/reportResult",
				        cache : false,
				        async : true,
				        headers: { 
				            'Accept': 'application/json',
				            'Content-Type': 'application/json' 
				        },
				        data: JSON.stringify(data),
				        dataType: 'json',
				        success: function (data) {
				        	  var dt=data.data;
				        	var table = document.createElement("TABLE");
				              table.border = "1";
				       
				              //Get the count of columns.
				              var columnCount = 6;
				       
				              //Add the header row.
				              var task = new Array();
				              task.push(["Id", "Project", "Priority","Severity","Status","Due Date"]);
				           var row = table.insertRow(-1);
				              for (var i = 0; i < columnCount; i++) {
				                  var headerCell = document.createElement("TH");
				                  headerCell.innerHTML = task[0][i];
				                  row.appendChild(headerCell);
				              }
				              
				            
				              var reportresult = new Array();
				              for (var x = 0; x < dt.length; x++) {
				            	  var id=dt[x].id;
				            	  var project=dt[x].project;
				            	  var pririty=dt[x].pririty;
				            	  var severity=dt[x].severity;
				            	  var status=dt[x].status;
				            	  var duedate=dt[x].duedate;
				            	  reportresult.push([id,project,pririty,severity,status,duedate]);
				              }
				              
				              
				              
				              //Add the data rows.
				            for (var i = 1; i < dt.length; i++) {
				                  row = table.insertRow(-1);
				                  for (var j = 0; j < columnCount; j++) {
				                      var cell = row.insertCell(-1);
				                      cell.innerHTML = reportresult[i][j] ;
				                    
				                     
				                  }
				              }
				       
				              var dvTable = document.getElementById("dvTable");
				              dvTable.innerHTML = "";
				              dvTable.appendChild(table);
				        	
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				        	toastr.error(errorThrown);

				        }
			
				  });	
	
	
	
	
});
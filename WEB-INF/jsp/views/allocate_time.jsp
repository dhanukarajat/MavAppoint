<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
	<div id='calendar'></div>
		<%@ page import= "uta.mav.appoint.TimeSlotComponent" %>
		<%@ page import= "uta.mav.appoint.PrimitiveTimeSlot" %>
		<%@ page import= "uta.mav.appoint.CompositeTimeSlot" %>
		<%@ page import= "uta.mav.appoint.beans.AdvisingSchedule" %>
		<%@ page import= "uta.mav.appoint.beans.Appointment" %>
		<%@ page import= "java.util.ArrayList" %>
	
		<!--  begin processing schedules -->
		<% ArrayList<TimeSlotComponent> schedules = (ArrayList<TimeSlotComponent>)session.getAttribute("schedules");
		   ArrayList<Appointment> appointments = (ArrayList<Appointment>)session.getAttribute("appointments");
		    				%><script>
		    				$(document).ready(function(){
		    					$('#calendar').fullCalendar({
		    						header: {
		    							left:'month,basicWeek,basicDay',
		    							right: 'today, prev,next',
		    							center: 'title'
		    						},
		    						displayEventEnd : {
		    							month: true,
		    							basicWeek: true,
		    							'default' : true,
		    						},
		    						eventMouseOver: function(event,jsEvent,view){
		    							$('.fc-event-inner', this).append('<div id=\"'+event.id+'\" class=\"hover-end\">'+$.fullCalendar.formatDate(event.end, 'h:mmt')+'</div>');
		    						},
		    						eventMouseout: function(event, jsEvent, view) {
		    						    $('#'+event.id).remove();
		    						},
		    						dayClick: function(date,jsEvent,view){
		    							document.getElementById("opendate").value = date.format('YYYY-MM-DD');
		    							$("#addTimeSlotModal").modal();
		    						}
		    		    			<%if (schedules != null){%>
		    						,
		    						eventClick: function(event,element){
		    							if (event.id >= 0){
		    							document.getElementById("StartTime2").value = event.start.format('HH:mm');
		    							document.getElementById("EndTime2").value = event.end.format('HH:mm');
		    							document.getElementById("pname").value = event.title;
		    							document.getElementById("Date").value = event.start.format('YYYY-MM-DD');
		    							$("#deleteTimeSlotModal").modal();
		    							}
		    							else{
		    								updateAppt.submit();
		    							}
		    						},
		    					events: [
		 		    		<% int i = 0;
									for (i=0;i<schedules.size();i++){%> 
		 								{
		 									title:'<%=schedules.get(i).getName()%>',
		 									start:'<%=schedules.get(i).getDate()+"T"+schedules.get(i).getStartTime()%>',
		 									end:'<%=schedules.get(i).getDate()+"T"+schedules.get(i).getEndTime()%>',
		 									id:<%=i%>,
		 									backgroundColor: 'blue'
		 								}
		 								<%if(i != (schedules.size()-1)||appointments != null){%>,<%}%>
		 					 		<%}	
									if (appointments != null){
										for(i=1;i<appointments.size()+1;i++){ //start array at 1 to prevent 0 id -> appointment id's must be negative%> 
		 									{
 												title:'<%=appointments.get(i-1).getAppointmentType()%>',
 												start:'<%=appointments.get(i-1).getAdvisingDate()+"T"+appointments.get(i-1).getAdvisingStartTime()%>',
 												end:'<%=appointments.get(i-1).getAdvisingDate()+"T"+appointments.get(i-1).getAdvisingEndTime()%>',
 												id:<%=-i%>,
 												backgroundColor: 'orange'
 											}
 											<%if(i != (appointments.size()-1)){%>,<%}
 										}
									}%>		 					 
		 					 			]<%}%>
		    					});
		    				});
	 						</script>	
		 						

	<form name=addTimeSlot action="availability" method="post" onsubmit="return false;">
	<div class="modal fade" id="addTimeSlotModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="addTimeSlotLabel">Add Time Slots</h4>
				</div>
				<div class="modal-body">
						<label for="starttime">Start Time:</label>
		 				<input type="time" class="form-control" name=starttime id="starttime" step="300">
		 				<label for="tndtime">End Time:</label>
		 				<input type="time" class="form-control" name=endtime id="endtime" step="300">
		 				<label for="opendate">Date:</label>
		 				<input type="text" class="form-control" name=opendate id="opendate">
		 				<label for="repeat">Weekly repeat duration:</label>
						<input type="text" class="form-control" name=repeat id="repeat"
						value="0">
						<label id="result"><font style="color:#e67e22" size="4"></label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal"> Close 
					</button>
					<input type="submit" value="submit" onclick="javascript:FormSubmit();">
				</div>
			</div>
		</div>
	</div>
	</form>
	<form name=deleteTimeSlot action="ts-manage" method="post" onsubmit="return false;">
	<div class="modal fade" id="deleteTimeSlotModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="deleteTimeSlotTitle">Delete Time Slot</h4>
				</div>
				<div class="modal-body">
						<label for="StartTime">Start Time:</label>
		 				<input type="time" class="form-control" name=StartTime2 id="StartTime2" step="300">
		 				<label for="EndTime">End Time:</label>
		 				<input type="time" class="form-control" name=EndTime2 id="EndTime2" step="300">
		 				<label for="Date">Date:</label>
		 				<input type="date" class="form-control" name=Date id="Date">
		 				<input type="hidden" name=pname id="pname">
		 				<label id="result2"><font style="color:#e67e22" size="4"></label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal"> Close 
					</button>
					<input type="submit" value="submit" onclick="javascript:validate();">
				</div>
			</div>
		</div>
	</div>
	</form>
	<script> function FormSubmit(){
									var starttime = document.getElementById("starttime").value;
									var endtime = document.getElementById("endtime").value;
									var date = document.getElementById("opendate").value;
									var repeat = document.getElementById("repeat").value;
									var params = ('starttime='+starttime+'&endtime='+endtime+'&opendate='+date+'&repeat='+repeat);
									var xmlhttp;
									xmlhttp = new XMLHttpRequest();
									xmlhttp.onreadystatechange=function(){
										if (xmlhttp.readyState==4){
											document.getElementById("result").innerHTML = xmlhttp.responseText;	
										}
									}
									xmlhttp.open("POST","availability",true);
									xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
									xmlhttp.setRequestHeader("Content-length",params.length);
									xmlhttp.setRequestHeader("Connection","close");
									xmlhttp.send(params);
									document.getElementById("result").innerHTML = "Attempting to add time slot...";
								}

					</script>
	<script>
	function validate(){
		var valid = confirm('Are you sure you want to delete?');	
		if (valid == true){
			var starttime = document.getElementById("StartTime2").value;
			var endtime = document.getElementById("EndTime2").value;
			var date = document.getElementById("Date").value;
			var pname = document.getElementById("pname").value;
			var params = ('StartTime2='+starttime+'&EndTime2='+endtime+'&Date='+date+'&pname='+pname);
			var xmlhttp;
			xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange=function(){
				if (xmlhttp.readyState==4){
					document.getElementById("result2").innerHTML = xmlhttp.responseText;	
				}
			}
			xmlhttp.open("POST","ts-manage",true);
			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xmlhttp.setRequestHeader("Content-length",params.length);
			xmlhttp.setRequestHeader("Connection","close");
			xmlhttp.send(params);
			document.getElementById("result2").innerHTML = "Attempting to delete time slot...";
			return false;
		}
		else {
			return false;
		}
	}
	</script>
<style>
	#calendar{
		background-color: white;
	}
</style>
<%@include file="templates/footer.jsp" %>
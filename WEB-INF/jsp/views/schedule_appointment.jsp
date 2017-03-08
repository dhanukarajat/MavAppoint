<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
<%@ page import= "uta.mav.appoint.login.LoginUser" %>
			<%@ page import= "uta.mav.appoint.login.AdminUser" %>
			<%@ page import= "uta.mav.appoint.login.AdvisorUser" %>
			<%@ page import= "uta.mav.appoint.login.StudentUser" %>
			<%@ page import= "uta.mav.appoint.login.FacultyUser" %>
			
			<% LoginUser user = (LoginUser)session.getAttribute("user"); %>
		<div class="input-group-btn">
		<%@ page import= "uta.mav.appoint.beans.AppointmentType" %>
		<% ArrayList<AppointmentType> ats = (ArrayList<AppointmentType>)session.getAttribute("appointmenttypes");
		if (ats != null){%>
			<button class="btn btn-default btn-lg dropdown-toggle" type="button" data-toggle="dropdown">
			Select Advising Type for <%=request.getParameter("pname")%> <span class="caret"></span>
			</button>
		<ul class="dropdown-menu" role="menu">
			<%for (int i=0;i<ats.size();i++){
				%><li><a href="?apptype=<%=ats.get(i).getType()%>&pname=<%=request.getParameter("pname")%>&advisor_email=<%=ats.get(0).getEmail()%>&id1=<%=request.getParameter("id1")%>&duration=<%=ats.get(i).getDuration()%>"><%=ats.get(i).getType()%></a></li> <%
			}
		}%>
		</ul>
		</div>
		<br><br><hr>
		<div id='calendar'></div>
   		<%@ page import= "java.util.ArrayList" %>
		<%@ page import= "uta.mav.appoint.TimeSlotComponent" %>
		<%@ page import= "uta.mav.appoint.PrimitiveTimeSlot" %>
		<%@ page import= "uta.mav.appoint.CompositeTimeSlot" %>
		
		<% TimeSlotComponent schedule = (TimeSlotComponent)session.getAttribute("timeslot");
		    			if (schedule != null){%>
		    				<script>
		    				$(document).ready(function(){
		    					$('#calendar').fullCalendar({
		    						defaultView : 'basicDay',
		    						viewRender: function(view, element){
		    							$('#calendar').fullCalendar('gotoDate','<%=schedule.getDate()%>');
		    						},
		    						displayEventEnd : {
		    							month: false,
		    							basicWeek: false,
		    							'default' : false,
		    						}<%if(request.getParameter("duration") != null){%>,
		    						eventClick: function(event,element){
		    							document.getElementById("id2").value = event.id;
		    							document.getElementById("apptype").value = '<%=request.getParameter("apptype")%>';
		    							document.getElementById("duration").value = '<%=request.getParameter("duration")%>';
		    							document.getElementById("pname").value = '<%=request.getParameter("pname")%>';
		    							document.getElementById("advisor_email").value = '<%=request.getParameter("advisor_email")%>';
		    							document.getElementById("start").value = event.start;
		    							document.getElementById("end").value = event.end;
		    							document.getElementById("starttime").value = event.start.format();
		    							document.getElementById("endtime").value = event.end.format();
		    							
		    							$('#addApptModal').modal();
		    							},
		    					events: [
		    					    <%=schedule.getEvent(Integer.parseInt(request.getParameter("duration"))/5)%>     
		    					        ]<%}%>	
		    					});
		    				});
	 						</script>	
		 					<%}%>
	<br/><br/><hr>
	<form name=addAppt action="schedule" method="post">
	<div class="modal fade" id="addApptModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id=addApptTypeLabel">Add Appointment</h4>
				</div>
				<div class="modal-body">
						<input type="hidden" name=id2 id="id2">
						<input type="hidden" name=apptype id="apptype">
						<input type="hidden" name=start id="start">
						<input type="hidden" name=end id="end">
						<input type="hidden" name=starttime id="starttime">
						<input type="hidden" name=endtime id="endtime">
						<input type="hidden" name=pname id="pname">
						<input type="hidden" name=duration id="duration">
						<input type="hidden" name=advisor_email id="advisor_email">
						Email address: <br><input type="text" name="email" id="email" value="<%= user.getEmail()%>"><br>
						UTA Student ID: <br><input type="text" name="studentid"> <br>
						Description: <br><textarea rows=4 columns="10" name="description"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal"> Close 
					</button>
					<input type="submit" value="Submit" onclick="javascript:FormSubmit();">
				</div>
			</div>
		</div>
	</div>
	</form>
<style>
	#calendar{
	background-color: white;
	}
</style>	
<script> function FormSubmit(){
									var student_email = document.getElementById("email").value;
									var advisor_email = document.getElementById("advisor_email").value;
									var starttime = document.getElementById("starttime").value;
									var endtime = document.getElementById("endtime").value;
									var params = ('student_email='+student_email+'&advisor_email='+advisor_email+'&starttime='+starttime+'&endtime='+endtime);
									var xmlhttp;
									xmlhttp = new XMLHttpRequest();
									xmlhttp.onreadystatechange=function(){
										
									}
									xmlhttp.open("POST","mail",true);
									xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
									xmlhttp.setRequestHeader("Content-length",params.length);
									xmlhttp.setRequestHeader("Connection","close");
									xmlhttp.send(params);
									alert("Meeting request sent.");
								}
								</script>

<%@include file="templates/footer.jsp" %>
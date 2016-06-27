<%@page import="uta.mav.appoint.email.Email"%>
<%@page import="uta.mav.appoint.login.LoginUser"%>
<%@page import="uta.mav.appoint.login.StudentUser"%>
<%@page import="uta.mav.appoint.db.DatabaseManager"%>

<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
<style>
    .custab{
        border: 1px solid #ccc;
        padding: 5px;
        margin: 5% 0;
        box-shadow: 3px 3px 2px #ccc;
        transition: 0.5s;
        background-color:#FFFFFF;
    }
    .custab:hover{
        box-shadow: 3px 3px 0px transparent;
        transition: 0.5s;
    }
    .profile{
        border: 1px solid #ccc;
        padding: 5px;
        margin: 5% 0;
        box-shadow: 3px 3px 2px #ccc;
        transition: 0.5s;
        background-color: #FFFFFF;
    }
    .urgent{
        background-color: #e67e22;
    }
</style>


<div class="container">
    <div class="row">
    <div class="col-lg-4 custyle">
        <%
            LoginUser user = (LoginUser)session.getAttribute("user");
            if (user instanceof StudentUser){
                DatabaseManager dbm = new DatabaseManager();
                StudentUser student = dbm.getStudent(user.getEmail());
                %>
                <img src="ImageServlet" width="200" height="200">
                <form method="submit" action="profilePicUpload.jsp">
                    <input type="hidden" name="email" value="<% out.print(student.getEmail());%>" />
                    <input type="submit" class="btn btn-default" value="Change Profile Picture">
                </form>
                    
                    <form role="form" action="UpdateStudentProfileServlet" method="POST">
                        <input type="hidden" name="old_email" value="<% out.print(student.getEmail());%>" />
                        <input type="text" name="email" class="form-control" 
                               placeholder="email" value="<% out.print(student.getEmail());%>"/>
                        <input type="text" name="course" class="form-control"
                               placeholder="course" value="<%= student.getCourse()%>" />
                        
                        <select class="form-control" name="carrier" value="<%=student.getCarrier()%>">
                            <option value="txt.att.net"
                                    <% 
                                    if (student.getCarrier().equals("txt.att.net")){
                                        out.print("selected");
                                    }
                                    %>
                                    >AT&T</option>
                            <option value="sms.alltelwireless.com"
                                    <% 
                                    if (student.getCarrier().equals("sms.alltelwireless.com")){
                                        out.print("selected");
                                    }
                                    %>
                                    >Alltel</option>
                            <option value="sms.myboostmobile.com"
                                    <% 
                                    if (student.getCarrier().equals("sms.myboostmobile.com")){
                                        out.print("selected");
                                    }
                                    %>
                                    >Boost Mobile</option>
                            <option value="text.republicwireless.com"
                                    <% 
                                    if (student.getCarrier().equals("text.republicwireless.com")){
                                        out.print("selected");
                                    }
                                    %>
                                    >Republic Wireless</option>
                            <option value="messaging.sprintpcs.com"
                                    <% 
                                    if (student.getCarrier().equals("messaging.sprintpcs.com")){
                                        out.print("selected");
                                    }
                                    %>
                                    >Sprint</option>
                            <option value="tmomail.net"
                                    <% 
                                    if (student.getCarrier().equals("tmomail.net")){
                                        out.print("selected");
                                    }
                                    %>
                                    >T-Mobile</option>
                            <option value="email.uscc.net"
                                    <% 
                                    if (student.getCarrier().equals("email.uscc.net")){
                                        out.print("selected");
                                    }
                                    %>
                                    >U. S. Cellular</option>
                            <option value="vtext.com"
                                    <% 
                                    if (student.getCarrier().equals("vtext.com")){
                                        out.print("selected");
                                    }
                                    %>
                                    >Verizon</option>
                            <option value="vmobl.com" <% 
                                    if (student.getCarrier().equals("vmobl.com")){
                                        out.print("selected");
                                    }
                                    %>>Virgin Mobile</option>
                        </select>
                        <input type="text" name="phone" class="form-control" 
                               placeholder="phone number" value="<% out.print(student.getPhone());%>"/>
                        <input type="submit" class="btn btn-default" value="Update Information">
                    </form>
                        
        <%
            }
        %>
    </div>
    
    <div class="col-md-8  custyle">
    <table class="table custab">
    <thead>
        <tr>
            <th>Advisor Name</th>
            <th>Appointment Date</th>
			<th>Start Time</th>
			<th>End Time</th>
			<th>Advising Type</th>
			<th>Advising Email</th>
			<th>Description</th>
			<th>UTA Student ID</th>
            <th>Student Email</th>
            <th class="text-center">Action</th>
        </tr>
    </thead>
            		<%@ page import= "java.util.ArrayList" %>
		    		<%@ page import= "uta.mav.appoint.beans.Appointment" %>
		    		<!-- begin processing appointments  -->
		    		<% ArrayList<Appointment> array = (ArrayList<Appointment>)session.getAttribute("appointments");
		    			if (array != null){%>
                                            <%for (int i=0;i<array.size();i++){ %>
                                            <tr class="<%= array.get(i).getHTMLClass()%>">
                                                <td><%=array.get(i).getPname()%></td>
                                                <td><%=array.get(i).getAdvisingDate()%></td>
                                                <td><%=array.get(i).getAdvisingStartTime()%></td>
                                                <td><%=array.get(i).getAdvisingEndTime()%></td>
                                                <td><%=array.get(i).getAppointmentType()%></td>
                                                <td><%=array.get(i).getAdvisorEmail()%></td>
                                                <td><%=array.get(i).getDescription() %></td>
                                                <td><%=array.get(i).getStudentid()%></td>
                                                <td><%=array.get(i).getStudentEmail()%></td>
                                                
                                                <td class="text-center">
                                                    <form method="submit" action="cancelSession.jsp">
                                                        <%
                                                            out.print("<input type='hidden' name='id' id='id' value='" + i + "'>");
                                                        %>
                                                        <input type="submit" value="Cancel" id="cancelBtn" class="btn btn-default">
                                                    </form>
                                                    <form method="submit" action="addToDefaulters.jsp">
                                                        <%
                                                            out.print("<input type='hidden' name='id' id='id' value='" + i + "'>");
                                                        %>
                                                        <input type="submit" value="Defaulter" id="syncBtn" class="btn btn-default">
                                                    </form>
                                                    <form method="submit" action="eventSync.jsp">
                                                        <%
                                                            out.print("<input type='hidden' name='id' id='id' value='" + i + "'>");
                                                        %>
                                                        <input type="submit" value="Sync with Calendar" id="syncBtn" class="btn btn-default">
                                                        </form>
                                                        <form method="submit" action="ToggleUrgencyServlet">
                                                        <%
                                                            out.print("<input type='hidden' name='id' id='id' value='" + i + "'>");
                                                        %>
                                                         <input type="submit" value="Toggle Urgency" id="toggleUrgency" class="btn btn-default">
                                                        </form>
                                                </td>
                                            </tr>
                                                        <script>
                                                                function emailSend(){
									var to = document.getElementById("to").value;
									var body = document.getElementById("email").value;
									var subject = document.getElementById("subject").value;
									var params = ('to='+to+'&body='+body+'&subject='+subject);
									var xmlhttp;
									xmlhttp = new XMLHttpRequest();
									xmlhttp.onreadystatechange=function(){
										if (xmlhttp.readyState==4){
											alert("Email sent.");	
											return false;
										}
									}
									xmlhttp.open("POST","notify",true);
									xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
									xmlhttp.setRequestHeader("Content-length",params.length);
									xmlhttp.setRequestHeader("Connection","close");
									xmlhttp.send(params);
								}
								</script>
								</div>
						<%	}
		    			}
		    			%> 
					 <!-- end processing advisors -->	 
					</table>
			</div>
</div>
	<form name=addAppt action="manage" onsubmit="return validate2()" method="post">
	<div class="modal fade" id="addApptModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id=addApptTypeLabel">Update Appointment</h4>
				</div>
				<div class="modal-body">
						<input type="hidden" name=id2 id="id2" readonly>
						<b>Type:</b><input type="label" name=apptype id="apptype" ><br>
						<b>Date:    </b><input type="label" name=date id="date" ><br>
						<b>Start:   </b><input type="label" name=start id="start" ><br>
						<b>End:     </b><input type="label" name=end id="end" ><br>
						<b>Advisor: </b><input type="label" name=pname id="pname" ><br>
						<b>UTA Student ID: </b><br><input type="text" name=studentid id="studentid"><br>
						<b>Description:</b><br><textarea rows=4 columns="10" name=description id="description"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal"> Close 
					</button>
					<input type="submit" value="Submit">
				</div>
			</div>
		</div>
	</div>
	</form>
<form name=emailSubmit onsubmit="return emailSend()">
<div class="modal fade" id="emailModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Send message</h4>
				</div>
				<div class="modal-body">
						<b>Subject:</b><br><input type=text name=subject id="subject"><br>
						<b>Message:</b><br><textarea rows=4 columns="10" name=email id="email"></textarea><br>
						<input type=hidden name=to id="to"><br>
						</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal"> Close 
					</button>
					<input type="submit" value="Submit">
				</div>
			</div>
		</div>
	</div>
</form>
</div>
<script>
function validate(){
		return confirm('Are you sure you want to delete this appointment?');	
	}
function validate2(){
		if (document.getElementById("studentid").value == ""){
			alert("Student ID is required.");
			return false;
		}
		if (document.getElementById("description").value.length > 100){
			alert("Description is too long, please shorten it.");
			return false;
		}
	}
</script>
<%@include file="templates/footer.jsp" %>
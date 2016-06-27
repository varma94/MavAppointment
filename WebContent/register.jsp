<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
<%@ page import="java.util.ArrayList"%>
<%@ page import="uta.mav.appoint.constants.Constants"%>


<%
 String message = (String)session.getAttribute("message");
 String[] questions = Constants.SEC_QUESTIONS;
%>
<style>
.resize {
width: 60%;
}
.resize-body {
width: 80%;
}


</style>

<div class="container block">
<!-- Panel -->
    <div class="panel panel-default resize center-block">
    <!-- Default panel contents -->
	<div class="panel-heading text-center"><h1>Register Student</h1></div>
        <form action="register" method="post" name="register_form">
		<div class="panel-body center-block resize-body">
			
                    <label for="message"><font color="#e67e22" size="4"><%=message%></font></label>
                    <div class="">

                        <input  type='hidden' id='submitted' name='submitted'/>
                        <script>
                            document.getElementById("submitted").value = true;
                        </script>

                        <label for="userid"><font color="#0" size="4">Student ID</font></label> 
                        <br>
                        <input type="text" class="form-control" name=userid placeholder="1000xxxxxx or 6000xxxxxx">

                        <div class="form-group">
                        <label for="password"><font color="#0" size="4">Password</font></label> 
                        <br>
                        <input type="password" class="form-control" name=password>

                        <label for="repeatPassword"><font color="#0" size="4">Repeat Password</font></label> 
                        <br>
                        <input type="password" class="form-control" name=repeatPassword>
                        </div>

                        <div class="form-group">
                        <label for="sq1"><font color="#0" size="4">Security Question 1</font></label>
                        <br>

                        <select class ="form-control" name="sq1">
                            <%
                                for (int x=0; x<questions.length; x++){
                                    String question = questions[x];
                                    out.print("<option value='" + x +"'>" + question + "</option>");
                                }
                                %>
                        </select>
                        <input type="text" class="form-control" name="sqa1" />
                        </div>

                        <div class="form-group">
                        <label for="sq2"><font color="#0" size="4">Security Question 2</font></label>
                        <br>
                        <select class ="form-control" name="sq2">
                            <%
                                for (int x=0; x<questions.length; x++){
                                    String question = questions[x];
                                    out.print("<option value='" + x +"'>" + question + "</option>");
                                }
                                %>
                        </select>
                        <input type="text" class="form-control" name="sqa2" />
                        </div>

                        <div class="form-group">
                        <label for="sq3"><font color="#0" size="4">Security Question 3</font></label>
                        <br>
                        <select class ="form-control" name="sq3">
                            <%
                                for (int x=0; x<questions.length; x++){
                                    String question = questions[x];
                                    out.print("<option value='" + x +"'>" + question + "</option>");
                                }
                                %>
                        </select>
                        <input type="text" class="form-control" name="sqa3" />
                        </div>

                        <div class="form-group">
                         <label for="phone"><font color="#0" size="4">Phone Number</font></label> 
                        <br>
                        <input type="text" class="form-control" name=phone>
                        </div>

                        <div class="form-group">
                        <label for="carrier"><font color="#0" size="4">Carrier</font></label>
                        <br>
                        <select class="form-control" name ="carrier">
                            <option value="txt.att.net">AT&T</option>
                            <option value="sms.alltelwireless.com">Alltel</option>
                            <option value="sms.myboostmobile.com">Boost Mobile</option>
                            <option value="text.republicwireless.com">Republic Wireless</option>
                            <option value="messaging.sprintpcs.com">Sprint</option>
                            <option value="tmomail.net">T-Mobile</option>
                            <option value="email.uscc.net">U. S. Cellular</option>
                            <option value="vtext.com">Verizon</option>
                            <option value="vmobl.com">Virgin Mobile</option>
                        </select>
                        </div>

                        <label for="ce"><font color="#0" size="4">Course Enrolled</font></label> 
                        <br>
                        <input type="text" class="form-control" name=course>

                        <label for="emailAddress"><font color="#0" size="4">Email Address</font></label> 
                        <br>
                        <input type="text" class="form-control" name=emailAddress placeholder="firstname.lastname@mavs.uta.edu"> 
                        <img src="simpleImg" />
                        <br>
                        <label for="answer"><font color="#0" size="4">Enter the text shown</font></label>
                        <br>
                        <input name="answer" class="form-control"/>
                    </div>     
		</div>
		<div  class="panel-footer text-center">
                    <input type="submit" class="btn-lg" value="Submit">
		</div>
        </form>
    </div>
</div>		
	

		<%@include file="templates/footer.jsp"%>

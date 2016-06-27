<% String name = (String)request.getAttribute("includeHeader");
    if (name == null){ 
        %>
        
        <%@ include file="templates/header.jsp" %>
        
        <%
    
    } else {
%>
<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />

<% } %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<div class="container">

	<div class="btn-group">
	<ul class="nav navbar-nav">
		<li><a class="home_link" href="http://www.uta.edu/"> <font style="color:#e67e22">UTA Home </font></a> </li>
		<li><a class="ga-track-link" href="http://www.uta.edu/mymav"><font style="color:#e67e22">My Mav</font></a> </li>
		<li><a class="ga-track-link" href="http://www.outlook.com/mavs.uta.edu"><font style="color:#e67e22">Student Email</font></a> </li>
                <li><a class="ga-track-link" href="http://www.uta.edu/uta/acadcal.php"><font style="color:#e67e22">Academic Calendar</font></a> </li>
	</ul>
	</div>
	<div style="color:#FF4500">
	<% Date date = new Date(); out.print( "<h2 align=\"center\">" +date.toString()+"</h2>"); %>
    </div>

	<div class="jumbotron">
  		<h1> Maverick Advising </h1>
	</div>
	
	<br>
	<h3><font color="black">Link to other departments</font></h3>
	<ul>
		<li><a href="http://www.uta.edu/bioengineering/"> <font style="color:#e67e22">Bioengineering</font></a></li>
		<li><a href="http://www.uta.edu/ce/"><font style="color:#e67e22">Civil Engineering</font></a></li>
		<li><a href="http://www.cse.uta.edu/"><font style="color:#e67e22">Computer Science and Engineering</font></a></li>
		<li><a href="http://www.uta.edu/ee/"><font style="color:#e67e22">Electrical Engineering</font></a></li>
		<li><a href="http://www.uta.edu/ie/"><font style="color:#e67e22">Industrial, Manufacturing, and Systems Engineering</font></a></li>
		<li><a href="http://www.uta.edu/mse/"><font style="color:#e67e22">Materials Science and Engineering</font></a></li>
		<li><a href="http://www.uta.edu/mae/"><font style="color:#e67e22">Mechanical and Aerospace Engineering</font></a></li>
	</ul>

	<ul>
		<li> <h4><font color="black"><b><a href="feedback">click here</a> to provide feedback.</b></font></h4>
		</li>
		<li> <h4><font color="black"><b><a href="report">click here</a> to report issues or bugs.</b></font></h4>
		</li>
	</ul>
</div>
<%@include file="templates/footer.jsp" %>
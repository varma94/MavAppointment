<jsp:include page='templates/header.jsp' />
<%@ page import="java.util.ArrayList"%>


<%
 String message = (String)session.getAttribute("message");
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
        <form action="OnlinePortalServlet" method="post" name="register_form" onsubmit="return checkCorrectHuman()">
		<div class="panel-body center-block resize-body">
			
					<label for="message"><font color="#e67e22" size="4"><%=message%></font></label>
						<div class="">
							<div class="form-group">
				
								<input  type='hidden' id='submitted' name='submitted'/>
								<script>
								document.getElementById("submitted").value = true;
							</script>
	
							<label for="userid"><font color="#0" size="4">Student ID</font></label> 
							<br>
							<input type="text" class="form-control" name=userid placeholder="1000xxxxxx or 6000xxxxxx">
							
							<label for="password"><font color="#0" size="4">Student Name</font></label> 
							<br>
							<input type="text" class="form-control" name=sname>
							
							<label for="repeatPassword"><font color="#0" size="4">Email Address</font></label> 
							<br>
							<input type="text" class="form-control" name=eaddr>
							
							<label for="emailAddress"><font color="#0" size="4">What is your question ?</font></label> 
							<br>
							<input type="text" class="form-control" name=question placeholder="Question ?"> 
						    <br>
						</div>
					</div>
				
				</div>
                                              
				<div  class="panel-footer text-center">
				    <input type="submit" class="btn-lg" value="Submit">
				</div>
			</form>
		</div>
</div>		
	

		<%@include file="templates/footer.jsp"%>

<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
	<label><font color="#e67e22" size="5"> Edit Advisor: </label>
			
	<form action="edit_advisor" method="post" name="edit_advisor_form" onsubmit="return false;">
		<div class="form-group">
					<label for="emailAddress"><font color="#e67e22" size="4">Email Address</label><br>
		 			<input type="text" style="width:350px;" class="form-control" id="emailAddress"
		 				placeholder="advisor@uta.edu">
				</div>
				<div>
					<label for="pname"><font color="#e67e22" size="4">Display Name</label><br>
		 			<input type="text" style="width:350px;"class="form-control" id="pname"
		 				placeholder="Dr. Advisor">
				</div>
				<div>
					<label for="role"><font color="#e67e22" size="4">Role</label><br>
		 			<input type="text" style="width:350px;"class="form-control" id="role"
		 				placeholder="User type">
				</div>
				<input type="submit" value="Update" onclick="javascript:FormSubmit();">
	</form>			 	
	<label id="result"><font color="#e67e22" size="4"></font></label>
	<script> function FormSubmit(){
									var email = document.getElementById("emailAddress").value;
									var pname = document.getElementById("pname").value;
									var role = document.getElementById("role").value;
									var params = ('emailAddress='+email+'&pname='+pname+'&role='+role);
									var xmlhttp;
									xmlhttp = new XMLHttpRequest();
									xmlhttp.onreadystatechange=function(){
										if (xmlhttp.readyState==4){
											document.getElementById("result").innerHTML = xmlhttp.responseText;	
										}
									}
									xmlhttp.open("POST","edit_advisor",true);
									xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
									xmlhttp.setRequestHeader("Content-length",params.length);
									xmlhttp.setRequestHeader("Connection","close");
									xmlhttp.send(params);
									document.getElementById("result").innerHTML = "Attempting to edit Advisor...";
								}
								</script>
<%@include file="templates/footer.jsp" %>
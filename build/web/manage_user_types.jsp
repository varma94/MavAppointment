<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
	<label><font color="#e67e22" size="5"> Manage User Types: </label>
			
	<form action="manage_user_types" method="post" name="manage_user_types" onsubmit="return false;">
		
				<div>
					<label for="role"><font color="#e67e22" size="4">User Type</font></label><br>
		 			<input type="text" style="width:350px;"class="form-control" id="role"
		 				placeholder="User Type">
				</div>
				<input type="submit" value="Add" onclick="javascript:FormSubmit();">
				<input type="submit" value="Edit" onclick="javascript:FormSubmit();">
				<input type="submit" value="Delete" onclick="javascript:FormSubmit();">
	</form>			 	
	<label id="result"><font color="#e67e22" size="4"></font></label>
	<script> function FormSubmit(){
									var role = document.getElementById("role").value;
									var params = ('&role='+role);
									var xmlhttp;
									xmlhttp = new XMLHttpRequest();
									xmlhttp.onreadystatechange=function(){
										if (xmlhttp.readyState==4){
											document.getElementById("result").innerHTML = xmlhttp.responseText;	
										}
									}
									xmlhttp.open("POST","manage_user_types",true);
									xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
									xmlhttp.setRequestHeader("Content-length",params.length);
									xmlhttp.setRequestHeader("Connection","close");
									xmlhttp.send(params);
									document.getElementById("result").innerHTML = "Attempting to add/edit/delete user type...";
								}
								</script>
<%@include file="templates/footer.jsp" %>
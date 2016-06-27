<jsp:include page='templates/header.jsp' /><style>
.custab{
    border: 1px solid #ccc;
    padding: 5px;
    margin: 5% 0;
    box-shadow: 3px 3px 2px #ccc;
    transition: 0.5s;
    background-color:#e67e22;
    }
.custab:hover{
    box-shadow: 3px 3px 0px transparent;
    transition: 0.5s;
    }
</style>


<div class="container">
    <div class="btn-group">
	<form action="waitshow" method="post" name="cancel">
	<input type=hidden name=cancel_button id="cancel_button">
    <input type=hidden name=edit_button id="edit_button">
    <div class="row col-md-16  custyle">
    <table class="table table-striped custab">
    <thead>
        <tr>
            <th>Advisor Name</th>
            
			
			<th>Advising Type</th>
			
			<th>Description</th>
			<th>UTA Student ID</th>
            <th>Student Email</th>
           
        </tr>
    </thead>
            		<%@ page import= "java.util.ArrayList" %>
		    		<%@ page import= "uta.mav.appoint.beans.WaitList" %>
		    		<!-- begin processing appointments  -->
		    		<% ArrayList<WaitList> array = (ArrayList<WaitList>)session.getAttribute("waitlist");
		    			if (array != null){%>
							<%for (int i=0;i<array.size();i++){ %>
							<tr>
                				<td><%=array.get(i).getPname()%></td>
                				
								<td><%=array.get(i).getAppointmentType()%></td>
								
								<td><%=array.get(i).getDescription() %></td>
								<td><%=array.get(i).getStudentid()%></td>
								<td><%=array.get(i).getStudentEmail()%></td>
								
							</tr>
								
						<%	}
		    			}
		    			%> 
					 <!-- end processing advisors -->	 
					</table>
				</form>
			</div>
		</div>
	

<script>

</script>
<%@include file="templates/footer.jsp" %>
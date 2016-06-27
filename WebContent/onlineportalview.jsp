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
	<form action="onlineportalview" method="post" name="cancel">
	<input type=hidden name=cancel_button id="cancel_button">
    <input type=hidden name=edit_button id="edit_button">
    <div class="row col-md-16  custyle">
        <h3> <font color="white">
            
           
    
            		<%@ page import= "java.util.ArrayList" %>
		    		<%@ page import= "uta.mav.appoint.beans.OnlinePortalView" %>
                                
		    		<!-- begin processing appointments  -->
		    		<% ArrayList<OnlinePortalView> array = (ArrayList<OnlinePortalView>)session.getAttribute("onlineportal");
		    			if (array != null){%>
							<%for (int i=0;i<array.size();i++){ %>
						
                                                        <label for="userid">Student ID </label><br>	<%=array.get(i).getStudentid()%></br>
                				
					<label
                                            for="emailAddress">Student Question </label>	<br>		<%=array.get(i).getQuestion()%></br>
                                                
								
							
								
				      <label
                                          for="emailAddress">Student Email </label><br>		<%=array.get(i).getStudentEmail()%></br>
								
							
								
						<%	}
		    			}
%> </font></h3>
					 <!-- end processing advisors -->	 
					
				</form>
			</div>
		</div>
	

<script>

</script>
<%@include file="templates/footer.jsp" %>
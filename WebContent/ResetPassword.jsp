<%@ include file="templates/header.jsp" %>
<%@page import="uta.mav.appoint.db.DatabaseManager"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.AbstractMap.SimpleEntry" %>
<%@page import="uta.mav.appoint.constants.Constants" %>
<%@page import="java.lang.String" %>
<%@page import="java.lang.Integer" %>
<style>
.panel-heading {
    padding: 5px 15px;
}

.panel-footer {
	padding: 1px 15px;
	color: #A0A0A0;
}

.profile-img {
	width: 96px;
	height: 96px;
	margin: 0 auto 10px;
	display: block;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
}
</style>

<%
    DatabaseManager dbm = new DatabaseManager();
    String email = request.getParameter("email");
    ArrayList<SimpleEntry<Integer, String>> answers;
    answers = dbm.getSecurityQuestions(email);
    String[] questions = Constants.SEC_QUESTIONS;
    %>

 <div class="container" style="margin-top:40px">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                        <strong> Password Reset Page</strong>
                </div>
                <div class="panel-body">
                    <form role="form" action="ResetPasswordServlet" method="POST">
                        
                        <div class="row">
                            <div class="center-block">
                                <img class="profile-img"
                                            src="img/mavblue.jpg" alt="">
                            </div>
                        </div>
                        
                        <fieldset>
                            <input type="hidden" name="email" value="<%=request.getParameter("email") %>" />
                            
                            <div class="form-group">
                                <label for="sqa1"><font color="#0" size="4"><%=questions[answers.get(0).getKey()] %></font></label>
                                <br>
                                <input type="text" class="form-control" name="sqa1" />
                            </div>

                            <div class="form-group">
                                <label for="sqa2"><font color="#0" size="4"><%=questions[answers.get(1).getKey()] %></font></label>
                                <br>
                                <input type="text" class="form-control" name="sqa2" />
                            </div>

                            <div class="form-group">
                                <label for="sqa3"><font color="#0" size="4"><%=questions[answers.get(2).getKey()] %></font></label>
                                <br>
                                <input type="text" class="form-control" name="sqa3" />
                            </div>
                        </fieldset>
                        
                        <fieldset>
                            
                            <div class="row">
                                <div class="col-sm-12 col-md-10  col-md-offset-1 ">
                                    <div class="form-group">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-lock"></i>
                                                </span>
                                                <input type="password" class="form-control" name=password placeholder="New Password">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Reset Password">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="panel-footer ">
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="templates/footer.jsp" %>
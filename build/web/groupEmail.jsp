<%-- 
    Document   : groupEmail
    Created on : Apr 25, 2016, 11:45:38 AM
    Author     : oguni
--%>
<%@include file="templates/header.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form action="SendGroupMailServlet" method="post">
                <input type="text" name="subject" placeholder="Subject" 
                       class="form-control" width="100%" height="400"/>
                <select class="form-control">
                    <option value="all">All Students</option>
                    <option value="today">Today's appointment holders</option>
                </select>
                <input type="text" name="message" class="form-control" 
                       placeholder="Type your message here" />
                <input type="submit" class="form-control" value="Send Emails" />
            </form>
        </div>
    </div>
</div>
<%@include file="templates/footer.jsp" %>
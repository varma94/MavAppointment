<%@ include file="templates/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <div class="col-lg-4 ">
            <form action="SendGroupMailServlet" method="post">
                <select class="form-control" name="recipients">
                    <option value="all">To: All Appointment Holders</option>
                    <option value="today">To: Today's Appointment Holders</option>
                </select>
                <input type="text" name="subject" placeholder="Subject" 
                       class="form-control" />
                
                <textarea rows="4" name="message" class="form-control"
                          placeholder="Enter Your Message Here"></textarea>
                <input type="submit" value="Send Emails" class="btn btn-default" />
            </form>
        </div>
    </div>
</div>
<%@include file="templates/footer.jsp" %>
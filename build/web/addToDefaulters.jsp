<%-- 
    Document   : addToDefaulters
    Created on : Apr 18, 2016, 4:48:45 PM
    Author     : oguni
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import= "uta.mav.appoint.beans.Appointment" %>
<%@ page import="uta.mav.appoint.email.Email" %>
<%@ page import="uta.mav.appoint.db.DatabaseManager" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add To Defaulters</title>
    </head>
    <body>
        <h1>Add To Defaulters</h1>
        
        <% 
    Appointment appt;
    ArrayList<Appointment> array = (ArrayList<Appointment>)session.getAttribute("appointments");
    if (array!=null){
        int index = Integer.parseInt(request.getParameter("id"));
        appt = array.get(index);
        String userEmail = appt.getStudentEmail();
        DatabaseManager dm = new DatabaseManager();
        dm.addDefaulter(userEmail);
        Email newEmail = new Email("Missed Appointment","You have missed your advising appointment. "
                + "You will be assessed a no-show fee of $20", userEmail);
        newEmail.sendMail();
    }
%>
        
    </body>
</html>

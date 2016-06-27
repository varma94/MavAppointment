<%-- 
    Document   : eventSync
    Created on : Apr 16, 2016, 9:31:34 AM
    Author     : oguni
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import= "uta.mav.appoint.beans.Appointment" %>
<%@ page import="uta.mav.appoint.email.Email" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Sync</title>
    </head>
    <body>
        <h1>Outlook Calendar Event Sync</h1>
        <% 
    Appointment appt;
    ArrayList<Appointment> array = (ArrayList<Appointment>)session.getAttribute("appointments");
    if (array!=null){
        int index = Integer.parseInt(request.getParameter("id"));
        appt = array.get(index);
        Email newEmail = new Email("Your Appointment","", appt.getStudentEmail());
        String date = appt.getAdvisingDate();
        String[] dateArray = date.split("-");
        String startTime = appt.getAdvisingStartTime();
        String[] startArray = startTime.split(":");
        String endTime = appt.getAdvisingEndTime();
        String[] endArray = endTime.split(":");
        
        Calendar start = Calendar.getInstance();
        start.set(Integer.parseInt(dateArray[0]), 
                Integer.parseInt(dateArray[1])-1, 
                Integer.parseInt(dateArray[2]), 
                Integer.parseInt(startArray[0]), 
                Integer.parseInt(startArray[1]), 
                Integer.parseInt(startArray[2]));
        start.add(Calendar.HOUR_OF_DAY, 5);
        Calendar end = Calendar.getInstance();
        end.set(Integer.parseInt(dateArray[0]), 
                Integer.parseInt(dateArray[1])-1, 
                Integer.parseInt(dateArray[2]), 
                Integer.parseInt(endArray[0]), 
                Integer.parseInt(endArray[1]), 
                Integer.parseInt(endArray[2]));
        end.add(Calendar.HOUR_OF_DAY, 5);
        newEmail.sendEventSync("You have an appointment", "You have an advising appointment with " + appt.getPname() + ".", start, end);
    }
    
%>
        
    </body>
</html>

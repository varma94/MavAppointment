<%-- 
    Document   : cancelSession
    Created on : Apr 16, 2016, 9:51:03 PM
    Author     : oguni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "uta.mav.appoint.beans.Appointment" %>
<%@ page import= "uta.mav.appoint.db.DatabaseManager" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cancel Appointment</title>
    </head>
    <body>
        <h1>Cancel Appointment</h1>
        
        <% 
    Appointment appt;
    ArrayList<Appointment> array = (ArrayList<Appointment>)session.getAttribute("appointments");
    if (array!=null){
        int index = Integer.parseInt(request.getParameter("id"));
        appt = array.get(index);
        DatabaseManager dm = new DatabaseManager();
        boolean result = dm.cancelAppointment(appt.getAppointmentId());
        if (result){
            response.sendRedirect("appointments");
        } else {
            response.sendRedirect("failure.jsp");
        }
    }
    
%>
    </body>
</html>

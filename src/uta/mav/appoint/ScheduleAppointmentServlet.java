package uta.mav.appoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;

public class ScheduleAppointmentServlet extends HttpServlet{
	private static final long serialVersionUID = -5925080374199613248L;
	HttpSession session;
	String header;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user == null){
			user = new LoginUser();
		}
		try{
			header = "templates/" + user.getHeader() + ".jsp";
			int id = Integer.parseInt(request.getParameter("id1"));
			ArrayList<TimeSlotComponent> array = (ArrayList<TimeSlotComponent>)session.getAttribute("schedules");
			DatabaseManager dbm = new DatabaseManager();
			ArrayList<AppointmentType> ats = dbm.getAppointmentTypes(request.getParameter("pname"));
			session.setAttribute("timeslot", array.get(id));
			session.setAttribute("appointmenttypes", ats);
		}
		catch(Exception e){
			System.out.printf(e.toString());
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/schedule_appointment.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		try{
			Appointment a = new Appointment();
			a.setAppointmentId(Integer.parseInt(request.getParameter("id2")));
			a.setStudentid(request.getParameter("studentid"));
			a.setDescription(request.getParameter("description"));
			a.setAppointmentType(request.getParameter("apptype"));
			a.setPname(request.getParameter("pname"));
			//a.setDescription(request.getParameter("description"));
			int d = Integer.parseInt(request.getParameter("duration"));
			String[] parts = (request.getParameter("start")).split(" ");
			a.setAdvisingDate(parts[3] + "-" + convertDate(parts[1]) + "-" + parts[2]);
			parts = parts[4].split(":");
			a.setAdvisingStartTime(parts[0] + ":" + parts[1]);
			a.setAdvisingEndTime(addTime(parts[0],parts[1],d));
                        
                        
                        
			String email = request.getParameter("email");
			DatabaseManager dbm = new DatabaseManager();
                        
                        LoginUser user = (LoginUser)session.getAttribute("user");
                        ArrayList<Object> appointments = dbm.getAppointments(user);
                        
                        boolean allowed = true;
                        int count = 0;
                        for (Object obj: appointments){
                            Appointment appt = (Appointment)obj;
                            
                            Calendar date = Calendar.getInstance();
                            String[] dateArray = a.getAdvisingDate().split("-");
                            date.set(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[2]));
                            
                            Calendar date2 = Calendar.getInstance();
                            String[] dateArray2 = appt.getAdvisingDate().split("-");
                            date2.set(Integer.parseInt(dateArray2[0]), Integer.parseInt(dateArray2[1]) - 1, Integer.parseInt(dateArray2[2]));
                            
                            if (date.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR)
                                    && date.get(Calendar.YEAR) == date2.get(Calendar.YEAR)){
                                allowed = false;
                                break;
                            }
                            
                            if (date.get(Calendar.WEEK_OF_YEAR) == date2.get(Calendar.WEEK_OF_YEAR)){
                                count++;
                            }
                            
                        }
                        if (count > 1 ){
                            allowed = false;
                        }
                        Boolean result = false;
                        
                        if (allowed == true){
                            result = dbm.createAppointment(a,email);
                        }
                        
			if (result == true){
				response.setHeader("Refresh","2; URL=advising");
				request.getRequestDispatcher("/success.jsp").forward(request,response);
			}
			else{
				response.setHeader("Refresh","2; URL=advising");
				request.getRequestDispatcher("/success.jsp").forward(request,response);
			}		
		}
		catch(Exception e){
			System.out.printf(e.toString());
		}
	}
	
	public String addTime(String hour, String minute, int add){
		String result = "";
		try{
		int h = Integer.parseInt(hour);
		int m = Integer.parseInt(minute);
		if (m + add >= 60){
			m = m+add-60;
			h++;
		}
		else{
			m = m+add;
		}
		result = h+":"+m;
		}
		catch(Exception e){
			
		}
		return result;
	}
	
	public String convertDate(String d){
		if (d.equals("Jan")){
			return "1";
		}if (d.equals("Feb")){
			return "2";
		}if (d.equals("Mar")){
			return "3";
		}if (d.equals("Apr")){
			return "4";
		}if (d.equals("May")){
			return "5";
		}if (d.equals("Jun")){
			return "6";
		}if (d.equals("Jul")){
			return "7";
		}if (d.equals("Aug")){
			return "8";
		}if (d.equals("Sep")){
			return "9";
		}if (d.equals("Oct")){
			return "10";
		}if (d.equals("Nov")){
			return "11";
		}if (d.equals("Dec")){
			return "12";
		}
		return null;
	}

}


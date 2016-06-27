package uta.mav.appoint;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.AppointmentVisitor;
import uta.mav.appoint.visitor.Visitor;

/**
 * Servlet implementation class AdvisingServlet
 */
public class ManageAppointmentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String header;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user != null){
			try{
//                                        String apptype =request.getParameter("apptype");
//                                        String date= request.getParameter("date");
//                                        String start= request.getParameter("start");
//                                        String end = request.getParameter("end");
//                                        String pname = request.getParameter("pname");
//                                        String studentid = request.getParameter("studentid");
//                                        String description = request.getParameter("description");
//                                        Appointment sets = new Appointment();
//                                        sets.setAppointmentType(apptype);
//                                        sets.setAdvisingDate(date);
//                                        sets.setAdvisingStartTime(start);
//                                        sets.setAdvisingEndTime(end);
//                                        sets.setPname(pname);
//                                        sets.setStudentid(studentid);
//                                        sets.setDescription(description);
					header = "templates/" + user.getHeader() + ".jsp";
					//must be logged in to see advisor schedules - safety concern
					DatabaseManager dbm = new DatabaseManager();
                                     //   Boolean result = dbm.updateAppointment(sets);
                                       
					ArrayList<String> array =  dbm.getAdvisors();
					if (array.size() != 0){
						session.setAttribute("advisors", array);
					}
					else{
						//no advisors for department?
					}
					ArrayList<TimeSlotComponent> schedules = dbm.getAdvisorSchedule("all");
					if (schedules.size() != 0){
						session.setAttribute("schedules", schedules);
					}
					Visitor v = new AppointmentVisitor();
					ArrayList<Object> appointments = user.accept(v,null);
					if (appointments.size() != 0){
						session.setAttribute("appointments", appointments);
					}
			}
			catch(Exception e){
				
			}
		}
		else{
			header = "templates/header.jsp";
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/advising.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user != null){
			try{
					header = "templates/" + user.getHeader() + ".jsp";
					Appointment a = new Appointment();
					a.setDescription(request.getParameter("description"));
					a.setStudentid(request.getParameter("studentid"));
					a.setAppointmentId(Integer.parseInt(request.getParameter("id2")));
					DatabaseManager dbm = new DatabaseManager();
					Boolean result = dbm.updateAppointment(a);
					if (result == true){
						response.setHeader("Refresh","2; URL=appointments");
						request.getRequestDispatcher("/success.jsp").forward(request,response);
					}
					else{
						response.setHeader("Refresh","2; URL=appointments");
						request.getRequestDispatcher("/failure.jsp").forward(request,response);
					}
				}
			catch(Exception e){
				System.out.printf("Error in ManageAppointment Post : " + e.toString());
			}
		}
		else{
			header = "templates/header.jsp";
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/advising.jsp").forward(request, response);
	}
}

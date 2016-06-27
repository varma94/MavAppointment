package uta.mav.appoint;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.AppointmentVisitor;
import uta.mav.appoint.visitor.CancelAppointmentVisitor;
import uta.mav.appoint.visitor.Visitor;

/**
 * Servlet implementation class ViewAppointmentServlet
 */
@WebServlet("/ViewAppointmentServlet")
public class ViewAppointmentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    HttpSession session;   
    String header;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAppointmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user != null){
			try{
				header = "templates/" + user.getHeader() + ".jsp";
				Visitor v = new AppointmentVisitor();
				ArrayList<Object> appointments = user.accept(v,null);
				if (appointments.size() != 0&&appointments != null){
					session.setAttribute("appointments", appointments);
				}
			}
			catch(Exception e){
				System.out.printf(e.toString());
			}
		}
		else{
			header = "templates/header.jsp";
		}
		
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/view_appointments.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user != null){
			try{
                                DatabaseManager dbm = new DatabaseManager();
				header = "templates/" + user.getHeader() + ".jsp";
				Visitor v = new CancelAppointmentVisitor();
				int id = Integer.parseInt(request.getParameter("cancel_button"));
                                String email = (request.getParameter("defaulter_button"));
                                if(email!=null)
                                {
                                 String[] array = dbm.getMailIds();
                                 for(int i=0;i<array.length;i++)
                                 {
                                     if(array[i].equalsIgnoreCase(email)){
                                         Email e = new Email("Penalty","You have been fined 20$ for not attending the Appointment",email);
                                          e.sendMail();}
                                 }
                                }
				user.accept(v,(Object)id);
				v = new AppointmentVisitor();
				ArrayList<Object> appointments = user.accept(v,null);
				if (appointments.size() != 0&&appointments != null){
					session.removeAttribute("appointments");
					session.setAttribute("appointments", appointments);
					response.setHeader("Refresh","2; URL=appointments");
					request.getRequestDispatcher("/success.jsp").forward(request,response);
				}
			}
		catch(Exception e){
			System.out.printf("Error in Servlet: " + e.toString()+"\n");
		}
		}
		else{
			header = "templates/header.jsp";
		}
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/view_appointments.jsp").forward(request,response);
	}
}

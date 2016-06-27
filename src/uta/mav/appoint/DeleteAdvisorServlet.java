package uta.mav.appoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.CreateAdvisorBean;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.AppointmentVisitor;
import uta.mav.appoint.visitor.CreateAdvisorVisitor;
import uta.mav.appoint.visitor.Visitor;

/**
 * Servlet implementation class DeleteAdvisorServlet
 */
public class DeleteAdvisorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String header;
	String page;
	HttpSession session;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user == null){
				user = new LoginUser();
				session.setAttribute("user", user);
				response.sendRedirect("/login.jsp");		
		}
		else{
			try{
				header = "templates/" + user.getHeader() + ".jsp";
			}
			catch(Exception e){
				System.out.printf(e.toString());
			}
		}
		
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/delete_advisor.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] advisor = request.getParameterValues("advisor");
		
		/**boolean advisorDeleted = DeleteAdvisorController.deleteAdvisor(advisor);
		if(advisorDeleted){
			response.setHeader("Refresh","2; URL=delete_advisor");
			request.getRequestDispatcher("/WEB-INF/jsp/views/success.jsp").forward(request,response);
		} else {
			response.setHeader("Refresh","2; URL=delete_advisor");
			request.getRequestDispatcher("/WEB-INF/jsp/views/failure.jsp").forward(request,response);
		}*/
	}

}

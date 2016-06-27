package uta.mav.appoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/WaitListServlet")
public class WaitListServlet extends HttpServlet {
    int j=0;
   
	private static final long serialVersionUID = 1L;
	HttpSession session;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		// Create cookies for first and last names.      
	     Cookie UserName = new Cookie("user_name",
	                      request.getParameter("emailAddress"));
	     UserName.setMaxAge(60*60*24);
	     
	     response.addCookie(UserName);
		request.getRequestDispatcher("/login.jsp").forward(request,response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String advisorName = request.getParameter("advisorName");
		String advisingType = request.getParameter("advisingType");
                String description = request.getParameter("description");
                String studentID = request.getParameter("studentID");
                String emailAddress = request.getParameter("studentEmail");
		GetSet sets = new GetSet();
                sets.setAdvisorName(advisorName);
                sets.setAdvisingType(advisingType);
                sets.setDescription(description);
		sets.setEmailAddress(emailAddress);
		sets.setStudentId(studentID);
		try{
			//call db manager and authenticate user, return value will be 0 or
			//an integer indicating a role
			DatabaseManager dbm = new DatabaseManager();
			String result = dbm.setWaitList(sets);
			if(result != null){
				
				//response.sendRedirect("index");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else{
				//redirect back to login if authentication fails
				//need to add a "invalid username or password" response
                               
                                    response.sendRedirect("AccountLocked.jsp");
                              
			}
		}
		catch(Exception e){
			System.out.println(e);
			response.sendRedirect("login");
		}
	}
}

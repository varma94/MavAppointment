/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import uta.mav.appoint.email.Email;

/**
 *
 * @author varma
 */
@WebServlet("/ForgotPasswordServlet")

public class ForgotPasswordServlet extends HttpServlet{
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
		String emailAddress = request.getParameter("emailAddress");
		
		try{
			//call db manager and authenticate user, return value will be 0 or
			//an integer indicating a role
			Email email = new Email("Password Reset Link","Copy and Paste this link in the browser \n http://localhost:8080/MavAppoint/ResetPassword.jsp?email="+emailAddress,emailAddress);
                        email.sendMail();
			
				//redirect back to login if authentication fails
				//need to add a "invalid username or password" response
				response.sendRedirect("login");
			
		}
		catch(Exception e){
			System.out.println(e);
			response.sendRedirect("login");
		}
	}
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.StudentUser;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

/**
 *
 * @author varma
 */
@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
	String email;
	String password;
        HttpSession session;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// recently added two lines
		session = request.getSession();
		session.setAttribute("message", "");
		request.setAttribute("includeHeader", "templates/header.jsp");

		request.getRequestDispatcher("/register.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

            DatabaseManager dbm = new DatabaseManager();
		email = request.getParameter("email");
		password = request.getParameter("password");
                String a1 = request.getParameter("sqa1");
                String a2 = request.getParameter("sqa2");
		String a3 = request.getParameter("sqa3");
                
                ArrayList<SimpleEntry<Integer, String>> answers = dbm.getSecurityQuestions(email);
                if (answers.get(0).getValue().equals(a1) &&
                        answers.get(1).getValue().equals(a2) &&
                        answers.get(2).getValue().equals(a3)){
                    StudentUser studentUser = new StudentUser();
                    try {
			studentUser.setEmail(email);
			studentUser.setPassword(password);
			
			if (dbm.updateStudent(studentUser)) {
				request.getRequestDispatcher("/success.jsp").forward(request,response);
			} else {
				session.setAttribute("message", "Account could not be created");
			}
                    } catch (Exception e) {
			System.out.println(e + " RegisterServlet");
                    }
                }else{
                    request.getRequestDispatcher("ResetPassword.jsp?email="+email).forward(request, response);
                }
		// need to add check for maverick email address
		// need to add check that both passwords match
		// need to redirect back to register with correct error message

		

	}

}

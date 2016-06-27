package uta.mav.appoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.email.Email;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/SendGroupMailServlet")
public class SendGroupMailServlet extends HttpServlet {
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
                DatabaseManager dbm = new DatabaseManager();
                String recipients = request.getParameter("recipients");
                String subject = request.getParameter("subject");
                String message = request.getParameter("message");
                LoginUser loginUser  = (LoginUser)session.getAttribute("user");
                LoginUser user = dbm.getUser(loginUser.getEmail());
		try{
			
                        ArrayList<Object> recipientEmails;
                        if (recipients.equals("all")){
                            recipientEmails = dbm.getFutureStudents(user.getUserId());
                        } else {
                            recipientEmails = dbm.getTodaysStudents(user.getUserId());
                        }
                        
                        Runnable r1 = new Runnable() {
                            @Override
                            public void run() {
                                    for(int i=0;i<recipientEmails.size();i++){
                                        Email e = new Email("From " + user.getEmail() + ": " 
                                        + subject, message, (String)recipientEmails.get(i));
                                        e.sendMail();
                                    }
                            }
                        };
                        r1.run();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		catch(Exception e){
			System.out.println(e);
			response.sendRedirect("login");
		}
	}
}

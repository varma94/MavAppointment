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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/NotifyServlet")
public class NotifyServlet extends HttpServlet {
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
                String name = request.getParameter("name");
                String email = request.getParameter("email");
		String notify = request.getParameter("notify1");
		//String password = request.getParameter("password");
		GetSet sets = new GetSet();
                sets.setEmailAddress(email);
		//sets.setNotify(notify);
		//sets.setPassword(password);
		try{
			//call db manager and authenticate user, return value will be 0 or
			//an integer indicating a role
                       
			DatabaseManager dbm = new DatabaseManager();
                        String[] array = dbm.getMailIds();
                        if(notify.equals("Yes")){
                        for(int i=0;i<array.length;i++){
                         Email e = new Email(name+" is unavailable","",array[i]);
                         e.sendMail();
                        }}
                        else
                        {
                         for(int i=0;i<array.length;i++){
                         Email e = new Email(name+" is available","",array[i]);
                         e.sendMail();
                        } 
                        }
                        
		//	String user = dbm.updateNotify(sets);
			if(array.length != 0){
				
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

package uta.mav.appoint;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;
//import uta.mav.appoint.team3.security.HashPassword;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String header;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		try{
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
					//System.out.printf(e.toString());
				}
			}
		}
		catch(Exception e){
			header = "templates/header.jsp";
			//System.out.println(e);
		}
		session.setAttribute("message", "");
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/change_password.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			session = request.getSession();
			LoginUser user = (LoginUser)session.getAttribute("user");
			DatabaseManager dbm = new DatabaseManager();
			String currentpassword = request.getParameter("currentpassword");
			//String hashedPassword = HashPassword.hashpass(currentpassword);
			String password = request.getParameter("password");
			String repeatpassword = request.getParameter("repeatpassword");
			
			/** if(password.length()>=8)
			{
				 if(user.verifyPassword(hashedPassword)){
					if(password.equals(repeatpassword))
					{
						hashedPassword = HashPassword.hashpass(repeatpassword);
						user.setPassword(hashedPassword);
						user.setValidated(1);
						dbm.updateUser(user);
	
						session.setAttribute("user", user);
						session.setAttribute("message", "Password changed");
						request.setAttribute("includeHeader", header);
						request.getRequestDispatcher("/WEB-INF/jsp/views/change_password.jsp").forward(request,response);
					}
					else
					{
						session.setAttribute("message", "Passwords do not match");
						request.setAttribute("includeHeader", header);
						request.getRequestDispatcher("/WEB-INF/jsp/views/change_password.jsp").forward(request,response);
					}
				} 
				else{
					session.setAttribute("message", "Password Invalid");
					request.setAttribute("includeHeader", header);
					request.getRequestDispatcher("/WEB-INF/jsp/views/change_password.jsp").forward(request,response);
				}
			} */
			if(password.length()<= 8)
			{
				session.setAttribute("message", "Password Must be 8 Characters long");
				request.setAttribute("includeHeader", header);
				request.getRequestDispatcher("/change_password.jsp").forward(request,response);
			}
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}
}

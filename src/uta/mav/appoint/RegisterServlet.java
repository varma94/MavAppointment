package uta.mav.appoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.login.StudentUser;
import nl.captcha.Captcha;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String email;
	String password;
	String rpassword;
        String phone;
        String carrier;
        String course;
	String role;
        int sq1;
        int sq2;
        int sq3;
        String sqa1;
        String sqa2;
        String sqa3;
	String studentId;
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

             Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
             request.setCharacterEncoding("UTF-8");
             String answer = request.getParameter("answer");
             if (!captcha.isCorrect(answer)) {
                 session.setAttribute("message", "Account could not be created");
                 response.setHeader("Refresh","2; URL=register");
		 request.getRequestDispatcher("/failure.jsp").forward(request,response);
             } else {
            
		email = request.getParameter("emailAddress");
		password = request.getParameter("password");
		rpassword = request.getParameter("repeatPassword");
		studentId = request.getParameter("userid");
                
                sq1 = Integer.parseInt(request.getParameter("sq1"));
                sq2 = Integer.parseInt(request.getParameter("sq2"));
                sq3 = Integer.parseInt(request.getParameter("sq3"));
                
                sqa1 = request.getParameter("sqa1");
                sqa2 = request.getParameter("sqa2");
                sqa3 = request.getParameter("sqa3");
                course = request.getParameter("course");
                phone = request.getParameter("phone");
                carrier = request.getParameter("carrier");
		role = "student";

		// need to add check for maverick email address
		// need to add check that both passwords match
		// need to redirect back to register with correct error message

		StudentUser studentUser = new StudentUser();
		studentUser.setRole(role);

		try {

			if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@mavs.uta.edu")) {
				System.out.println("Email Address Invalid");
				session.setAttribute("message", "Email Address Invalid");
				//request.setAttribute("error", "Unable to add user");
				response.setHeader("Refresh","2; URL=register");
				request.getRequestDispatcher("/failure.jsp").forward(request,response);
				//request.getRequestDispatcher("/register.jsp").forward(request,
					//	response);
			}
			studentUser.setEmail(email);

			if (!((password).equals(rpassword))) {
				System.out.println("Password Does Not Match");
				session.setAttribute("message", "Password Does Not Match");
				request.setAttribute("error", "Unable to add user");
				response.setHeader("Refresh","2; URL=register");
				request.getRequestDispatcher("/failure.jsp").forward(request,response);
				//request.getRequestDispatcher("/register.jsp").forward(request,
					//	response);
			}
			studentUser.setPassword(password);

			if (!studentId.matches("^100\\d{7}")
					&& !request.getParameter("student_Id").matches(
							"^6000\\d{6}")) {
				System.out.println("StudentID Invalid");
				session.setAttribute("message", "StudentID Invalid");
				//request.setAttribute("error", "Unable to add user");
				response.setHeader("Refresh","2; URL=register");
				request.getRequestDispatcher("/failure.jsp").forward(request,response);
				//request.getRequestDispatcher("/register.jsp").forward(request,
					//	response);
			}
			studentUser.setStudentId(studentId);
                         studentUser.setPhone(phone);
                          studentUser.setCourse(course);
                          studentUser.setCarrier(carrier);
                        

			DatabaseManager dbm = new DatabaseManager();
			if (dbm.createStudent(studentUser) && dbm.insertSecQuestions(email, sq1, sq2, sq3, sqa1, sqa2, sqa3)) {
				Email userEmail = new Email("MavAppoint Account Created",
						"Your account for MavAppoint has been created! Your account information is:\n"
								+ "Role: " + role + "\n" + "Password: "
								+ password, email);
				userEmail.sendMail();
				response.setHeader("Refresh","2; URL=login");
				request.getRequestDispatcher("/success.jsp").forward(request,response);
				//session.setAttribute("message",
					//	"Account Created! Please check your E-mail.");
			} else {

				session.setAttribute("message", "Account could not be created");
			}
		} catch (Exception e) {
			System.out.println(e + " RegisterServlet");
		}
             }
	}

}

package servlet.process.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.EmployeeDAO;
import model.Employee;
import services.AESAlgorithm;
import services.EmailGenerator;
import services.PasswordService;
import services.SendEmail;

/**
 * Servlet implementation class ProcessResetPasswordServlet
 */
@WebServlet("/ProcessResetPasswordServlet")
public class ProcessResetPasswordServlet extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessResetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		
		String emailAddress = (String)request.getParameter("email");
		String[] email = {emailAddress};
		session.setAttribute("email", emailAddress);
		
		EmployeeDAO employeedao = new EmployeeDAO();
		try{
			Employee employee = employeedao.getEmployeeByEmail(emailAddress);
			SendEmail javaEmail = new SendEmail();
			
			if (employee == null) {
				throw new Exception("You have not registered an account");
			}
				String appUrl = 
					      "http://" + request.getServerName() + 
					      ":" + request.getServerPort() + 
					      request.getContextPath();
				String token = UUID.randomUUID().toString();
				String url = constructResetTokenEmail(appUrl, emailAddress, request.getLocale(), token);
				
				javaEmail.setMailServerProperties();
				javaEmail
						.sendEmail(
								"DABAO Web App - Password Reset",
								"Dear User,<br><br>"
								+ "To reset your password for DABAO App, please click the following link:<br> "
								+ "<a href=" + url +">" + url + "</a>"
								+"<br><br>"
								+ "Regards,<br>"
								+ "Admin<br><br>"
								+ "This is a system-generated email; please DO NOT REPLY to this email.<br>",
								email);
				session.setAttribute("success", "An email has been sent to you. Please check your email for instructions on resetting your password.");
				response.sendRedirect("login.jsp");
			
		}catch(Exception e){
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("resetPassword.jsp");
		}
	}
	
	private String constructResetTokenEmail(String contextPath, String email, Locale locale, String token) {
		//String emailEncrypt = PasswordService.encryptPassword("email");
		//String eEncrypt = PasswordService.encryptPassword(email);
		AESAlgorithm aes = new AESAlgorithm();
		String eEncrypt = aes.encrypt(email);
	    String url = contextPath + "/ProcessResetPasswordPage?email="+ eEncrypt + "&token=" + token;
	    return url;
	}	
}

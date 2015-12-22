package servlet;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.EmployeeController;
import dao.EmployeeDAO;
import model.Employee;
import services.AESAlgorithm;
import services.SendEmail;
import value.StringValues;

/**
 * Servlet implementation class ProcessSetDefaultBuilding
 */
@WebServlet("/ProcessSetDefaultDeliveryPointServlet")
public class ProcessSetDefaultDeliveryPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessSetDefaultDeliveryPointServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String buildingName = (String) request.getParameter("deliveryPoint");
		
		String email = (String) session.getAttribute("email");
		
		EmployeeController userController = new EmployeeController();

		//session.removeAttribute("email");

		userController.updateDefaultDeliveryPoint(email, buildingName);
		
		
		SendEmail javaEmail = new SendEmail();
		String[] emailArray = {email};
		
		
		try {
		String appUrl = 
			      "http://" + request.getServerName() + 
			      ":" + request.getServerPort() + 
			      request.getContextPath();
		
		String token = UUID.randomUUID().toString();
		
		String url = constructVerifyEmail(appUrl, email, request.getLocale(), token);
		
		javaEmail.setMailServerProperties();
		javaEmail
				.sendEmail(
						"DABAO Web App - Verify Your Email",
						"Dear User,<br><br>"
						+ "Welcome to DABAO App, please click the following link to verify your email address:<br><br> "
						+ "<a href=" + url + ">" + url + "</a>"
						+"<br><br>"
						+ "Regards,<br>"
						+ "Admin<br><br>"
						+ "This is a system-generated email; please DO NOT REPLY to this email.<br>",
						emailArray);
		
		session.removeAttribute("email");
		
		session.setAttribute("success", "An email has been sent to you. Please follow the instructions on verifying your account.");
		response.sendRedirect("login.jsp");
		} catch (Exception e) {
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("login.jsp");
		}

	}
	
	
	private String constructVerifyEmail(String contextPath, String email, Locale locale, String token) {
		AESAlgorithm aes = new AESAlgorithm();
		String eEncrypt = aes.encrypt(email);
		String encryptedStatus = aes.encrypt(StringValues.EMPLOYEE_OK);
	    String url = contextPath + "/ProcessVerificationServlet?email="+eEncrypt + "&status=" + encryptedStatus + "&token=" + token;
	    return url;
	}	

}

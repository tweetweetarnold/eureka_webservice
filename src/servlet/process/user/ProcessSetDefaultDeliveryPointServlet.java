package servlet.process.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import services.AESAlgorithm;
import services.SendEmail;
import value.StringValues;
import controller.AccessController;
import controller.EmployeeController;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		AccessController accessController = new AccessController();
		// JSONObject userInput = new JSONObject();

		String buildingName = (String) request.getParameter("deliveryPoint");

		HashMap<String, String> userInput = (HashMap<String, String>) session
				.getAttribute("userInput");

		String email = (String) userInput.get("email");
		String password = (String) userInput.get("password");
		String employeeName = (String) userInput.get("name");
		Long contactNumber = Long.parseLong(userInput.get("contactNo"));
		String companyCode = (String) userInput.get("companyCode");

		try {
			String generatedEmployeeId = accessController.registerUser(password, employeeName,
					email, contactNumber, companyCode, buildingName);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		EmployeeController userController = new EmployeeController();

		userController.updateDefaultDeliveryPoint(email, buildingName);

		
		String[] emailArray = { email };
		
		try {
			accessController.constructVerifyEmail(request.getServerName(), request.getServerPort(), request.getContextPath(), email, emailArray);

			session.removeAttribute("email");

			session.setAttribute("success",
					"An email has been sent to you. Please check your email within 5 minutes and follow the instructions to verify your account.");
			response.sendRedirect("/eureka_webservice/pages/login.jsp");
		} catch (Exception e) {
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/pages/login.jsp");
		}

	}

//	private String constructVerifyEmail(String contextPath, String email, Locale locale,
//			String token) {
//		AESAlgorithm aes = new AESAlgorithm();
//		String eEncrypt = aes.encrypt(email);
//		String encryptedStatus = aes.encrypt(StringValues.EMPLOYEE_OK);
//		String url = contextPath + "/eureka_webservice/ProcessVerificationServlet?email="
//				+ eEncrypt + "&status=" + encryptedStatus + "&token=" + token;
//		return url;
//	}

}

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.RegistrationController;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		JSONObject returnObj = new JSONObject();
		// PrintWriter out = response.getWriter();
		System.out.println("RegistrationServlet");

		try {
			RegistrationController registrationController = new RegistrationController();
			JSONObject jsonMessage = new JSONObject();

			String employeeName = (String) request.getParameter("name");
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String confirmPwd = (String) request.getParameter("confirmPwd");
			String contactNo = (String) request.getParameter("contactNo");
			String bankAcc = (String) request.getParameter("bankAcc");
			String companyName = (String) request.getParameter("company");

			jsonMessage.put("name", employeeName);
			jsonMessage.put("username", username);
			jsonMessage.put("contactNo", contactNo);
			jsonMessage.put("bankAcc", bankAcc);
			jsonMessage.put("company", companyName);

			returnObj.put("message", jsonMessage);

			// Check user parameters
			boolean validation = !(employeeName.isEmpty() && username.isEmpty()
					&& password.isEmpty() && confirmPwd.isEmpty() && contactNo.isEmpty()
					&& bankAcc.isEmpty() && companyName.isEmpty() && password.equals(confirmPwd) && password
					.length() < 7);

			if (validation) {
				 long contactNumber = Long.parseLong(contactNo);
				//long contactNumber = 123; // for testing
				 long creditCardNumber = Long.parseLong(bankAcc);
				int generatedEmployeeId = registrationController.registerUser(username, password,
						employeeName, creditCardNumber, contactNumber, companyName);

				//System.out.println("User created successfully. UserID: " + generatedEmployeeId);
				returnObj.put("success", "User created successfully. UserID: " + generatedEmployeeId );
				returnObj.put("status", "ok");
				HttpSession session = request.getSession();
				session.setAttribute("success", returnObj);
				
				response.sendRedirect("/eureka_webservice/registration.jsp");
				//System.out.println(returnObj.toString());

			} else {
				// response.sendRedirect("/eureka_webservice/registration.jsp");
				System.out.println("RegistrationServlet: Validation failed.");
				throw new Exception(
						"Please ensure that you have inserted valid characters into the fields.");
			}

		} catch (Exception e) {
			System.out.println("Exception@RegistrationServlet: " + e.getMessage());
			e.printStackTrace();
			returnObj.put("error", e.getMessage());
			returnObj.put("status", "fail");
			HttpSession session = request.getSession();
			session.setAttribute("error", returnObj);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(returnObj));

			response.sendRedirect("/eureka_webservice/registration.jsp");
		}
	}
}

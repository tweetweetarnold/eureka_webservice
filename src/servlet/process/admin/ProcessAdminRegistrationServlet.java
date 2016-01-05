package servlet.process.admin;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.AccessController;

/**
 * Servlet implementation class ProcessAdminRegistrationServlet
 */
@WebServlet("/ProcessAdminRegistrationServlet")
public class ProcessAdminRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminRegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccessController registrationController = new AccessController();

		System.out.println("AdminRegistrationServlet");

		HttpSession session = request.getSession();
		HashMap<String, String> userInput = new HashMap<>();

		try {
			String adminName = (String) request.getParameter("name");
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String confirmPwd = (String) request.getParameter("confirmPwd");
			String contactNo = (String) request.getParameter("contactNo");

			userInput.put("name", adminName);
			userInput.put("username", username);
			userInput.put("contactNo", contactNo);

			// Check user parameters
			boolean valid = (contactNo.length() == 8 && password.length() >= 7 && password
					.equals(confirmPwd));

			if (valid) {
				long contactNumber = Long.parseLong(contactNo);

				String generatedAdminId = registrationController.registerAdmin(username, password,
						adminName, contactNumber);

				session.setAttribute("success", "Yay! Your account has been created. Username: "
						+ generatedAdminId);

				response.sendRedirect("adminLogin.jsp");

			} else {
				System.out.println("RegistrationServlet: Validation failed.");
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("Exception@AdminRegistrationServlet: " + e.getMessage());
			e.printStackTrace();

			session.setAttribute("userInput", userInput);
			session.setAttribute("error", "Oops! Something went wrong! Please check your inputs.");

			response.sendRedirect("adminRegistration.jsp");
		}
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.RegistrationController;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/ProcessRegistrationServlet")
public class ProcessRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessRegistrationServlet() {
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

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("RegistrationServlet");

		HttpSession session = request.getSession();
		HashMap<String, String> userInput = new HashMap<>();

		try {
			RegistrationController registrationController = new RegistrationController();

			String employeeName = (String) request.getParameter("name");
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String confirmPwd = (String) request.getParameter("confirmPwd");
			String contactNo = (String) request.getParameter("contactNo");
			String bankAcc = (String) request.getParameter("bankAcc");
			String companyName = (String) request.getParameter("company");

			userInput.put("name", employeeName);
			userInput.put("username", username);
			userInput.put("contactNo", contactNo);
			userInput.put("bankAcc", bankAcc);
			userInput.put("company", companyName);

			// Check user parameters
			boolean validation = !(employeeName.isEmpty() && username.isEmpty()
					&& password.isEmpty() && confirmPwd.isEmpty() && contactNo.isEmpty()
					&& contactNo.length() == 8 && bankAcc.isEmpty() && companyName.isEmpty()
					&& password.equals(confirmPwd) && password.length() < 7);

			if (validation) {
				long contactNumber = Long.parseLong(contactNo);

				long creditCardNumber = Long.parseLong(bankAcc);
				String generatedEmployeeId = registrationController.registerUser(username,
						password, employeeName, creditCardNumber, contactNumber, companyName);

				session.setAttribute("success", "User created successfully. UserID: "
						+ generatedEmployeeId);

				response.sendRedirect("registration.jsp");

			} else {
				System.out.println("RegistrationServlet: Validation failed.");
				throw new Exception();
			}

		} catch (Exception e) {
			System.out.println("Exception@RegistrationServlet: " + e.getMessage());
			e.printStackTrace();

			session.setAttribute("userInput", userInput);
			session.setAttribute("error", "Oops! Something went wrong! Please check your inputs.");

			response.sendRedirect("registration.jsp");
		}
	}
}

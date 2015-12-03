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

import org.apache.commons.validator.routines.EmailValidator;

import controller.AccessController;

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
		System.out.println("*********** RegistrationServlet ***********");

		HttpSession session = request.getSession();
		HashMap<String, String> userInput = new HashMap<>();

		try {
			AccessController accessController = new AccessController();

			String employeeName = (String) request.getParameter("name").trim();
			String password = (String) request.getParameter("password");
			String confirmPwd = (String) request.getParameter("confirmPwd");
			String contactNo = (String) request.getParameter("contactNo");
			String email = (String) request.getParameter("email").trim();
			String companyCode = (String) request.getParameter("companyCode");

			userInput.put("name", employeeName);
			userInput.put("email", email);
			userInput.put("contactNo", contactNo);
			userInput.put("companyCode", companyCode);

			// Check valid email
			// String dotCom = email.substring(email.length() - 4,
			// email.length());
			// boolean validEmail = (dotCom.equalsIgnoreCase(".com"));

			EmailValidator emailValidator = EmailValidator.getInstance();
			boolean validEmail = emailValidator.isValid(email);

			// Check user parameters
			boolean validEmployeeName = (!employeeName.equals("")) && employeeName.length() > 0;

			boolean valid = (contactNo.length() == 8 && password.length() >= 7
					&& password.equals(confirmPwd) && validEmail);
			boolean validContactNo = contactNo.length() == 8;
			boolean validPasswordLength = password.length() >= 7;
			boolean validPasswordConfirmation = password.equals(confirmPwd);

			if (validEmployeeName) {
				if (validContactNo) {
					long contactNumber = Long.parseLong(contactNo);
					if (validPasswordLength) {
						if (validPasswordConfirmation) {
							if (validEmail) {
								String generatedEmployeeId = accessController.registerUser(
										password, employeeName, email, contactNumber, companyCode);
								session.setAttribute("email", email);
								session.setAttribute("companyCode", companyCode);
								session.setAttribute("success",
										"Your account has been created. Username: "
												+ generatedEmployeeId);

								response.sendRedirect("RetrieveBuildingsServlet");
							} else {
								System.out.println("RegistrationServlet: Validation failed.");
								throw new Exception("The Email that you provided is not valid");
							}
						} else {
							System.out.println("RegistrationServlet: Validation failed.");
							throw new Exception("Passwords do not match");
						}
					} else {
						System.out.println("RegistrationServlet: Validation failed.");
						throw new Exception("Password must be at least 7 characters long");
					}
				} else {
					System.out.println("RegistrationServlet: Validation failed.");
					throw new Exception("Contact Number must be 8 digits");
				}
			} else {
				System.out.println("RegistrationServlet: Validation failed.");
				throw new Exception("Employee name cannot be empty");
			}

			// if (valid) {
			// long contactNumber = Long.parseLong(contactNo);
			//
			// String generatedEmployeeId =
			// registrationController.registerUser(username,
			// password, employeeName, email, contactNumber, companyCode);
			//
			// session.setAttribute("success",
			// "Yay! Your account has been created. Username: "
			// + generatedEmployeeId);
			//
			// response.sendRedirect("login.jsp");
			//
			// } else {
			// System.out.println("RegistrationServlet: Validation failed.");
			// throw new Exception();
			// }

		} catch (Exception e) {
			System.out.println("Exception@RegistrationServlet: " + e.getMessage());
			e.printStackTrace();
			if (!e.getMessage().isEmpty()) {
				session.setAttribute("error", e.getMessage());
			} else {
				session.setAttribute("error",
						"Oops! Something went wrong! Please check your inputs.");
			}
			session.setAttribute("userInput", userInput);

			response.sendRedirect("registration.jsp");
		}
	}
}

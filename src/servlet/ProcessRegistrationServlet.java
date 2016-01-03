package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Company;

import org.apache.commons.validator.routines.EmailValidator;

import controller.AccessController;
import controller.CompanyController;

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

		response.setContentType("text/html");
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

			EmailValidator emailValidator = EmailValidator.getInstance();
			boolean validEmail = emailValidator.isValid(email);

			// Check user parameters
			boolean validEmployeeName = (!employeeName.equals("")) && employeeName.length() > 0;

			boolean valid = (contactNo.length() == 8 && password.length() >= 7
					&& password.equals(confirmPwd) && validEmail);
			boolean validContactNo = contactNo.matches("[689][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
			boolean validPasswordLength = password.length() >= 7;
			boolean validPasswordConfirmation = password.equals(confirmPwd);

			if (validEmployeeName) {
				if (validContactNo) {
					long contactNumber = Long.parseLong(contactNo);
					if (validPasswordLength) {
						if (validPasswordConfirmation) {
							if (validEmail) {
								// moved creation of user to next servlet
								// String generatedEmployeeId = accessController.registerUser(
								// password, employeeName, email, contactNumber, companyCode);

								userInput.put("password", password);
								session.setAttribute("userInput", userInput);

								CompanyController companyController = new CompanyController();

								Company company = companyController
										.getCompanyByCompanyCode(companyCode);
								Set<String> buildingSet = company.getDeliveryPointSet();
								System.out.println("Building size: " + buildingSet.size());

								RequestDispatcher rd = request
										.getRequestDispatcher("defaultDeliveryPoint.jsp");
								request.setAttribute("buildingSet", buildingSet);
								rd.forward(request, response);

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
					throw new Exception("Contact Number is not valid.");
				}
			} else {
				System.out.println("RegistrationServlet: Validation failed.");
				throw new Exception("Employee name cannot be empty");
			}

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

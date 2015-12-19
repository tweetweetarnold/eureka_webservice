package servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import value.StringValues;
import model.Canteen;
import model.Employee;
import controller.AccessController;
import controller.CanteenController;
import controller.OrderWindowController;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/ProcessLoginServlet")
public class ProcessLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		AccessController accessController = new AccessController();
		CanteenController canteenController = new CanteenController();
		OrderWindowController orderWindowController = new OrderWindowController();

		String email = "";
		String inputPwd = "";

		try {
			// Getting User Input Parameters
			email = (String) request.getParameter("email");
			inputPwd = (String) request.getParameter("password");

			// (1) VERIFY USER CREDENTIALS. If user does not exist, returns null.
			Employee emp = accessController.authenticateUser(email, inputPwd);
			if (emp == null) {
				throw new Exception("Authentication failed. Please check your credentials.");
			}
			System.out.println("User is authenticated: " + emp.getName());

			// (2) CHECK USER STATUS. If user status is not ok, do something
			String empStatus = emp.getStatus();
			switch (empStatus) {
			case StringValues.EMPLOYEE_PENDING_VERIFICATION:
				throw new Exception("Account not verified. Please verify your account first!");

			case StringValues.EMPLOYEE_SUSPENDED:
				throw new Exception(
						"Account has been suspended. Please contact your administrator for help.");

			case StringValues.EMPLOYEE_DESTROYED:
				throw new Exception("Account has been disabled.");
			}

			// (3) VERIFY AVAILABLE OPENED ORDER WINDOW.
			boolean availableWindow = orderWindowController.checkForActiveWindow(emp.getCompany());
			if (!availableWindow) {
				throw new Exception("There are no available Order Windows Opened for your company.");
			}

			// *** For Development only ***
			// creates a tokenID using UUID (Universalised Unique Identifier
			// Object)
			// the user's username is tagged at the end of the token
			String tokenID = UUID.randomUUID().toString().toUpperCase() + "|" + emp.getEmail()
					+ "|";

			// Setting user and token
			session.setAttribute("user", emp);
			session.setAttribute("tokenID", tokenID);
			System.out.println("TokenID is set in session");

			// for login2
			List<Canteen> canteenList = canteenController.getAllCanteens();
			System.out.println("canteen size: " + canteenList.size());
			session.setAttribute("canteenList", canteenList);

			response.sendRedirect("homepage.jsp");

		} catch (Exception e) {
			String errorMessage = e.getMessage();

			System.out.println("Exception@ProcessLoginServlet");
			System.out.println("Exception message: " + errorMessage);
			e.printStackTrace();

			session.setAttribute("email", email);

			if (errorMessage == null) {
				errorMessage = "Something went wrong! Please contact the administrator for help.";
			}

			session.setAttribute("error", errorMessage);
			response.sendRedirect("login.jsp");
		}

	}
}

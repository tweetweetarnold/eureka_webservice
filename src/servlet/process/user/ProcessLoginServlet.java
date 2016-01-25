package servlet.process.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Canteen;
import model.Employee;
import model.OrderWindow;

import org.json.JSONException;
import org.json.JSONObject;

import value.StringValues;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.AccessController;
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		AccessController accessController = new AccessController();
		OrderWindowController orderWindowController = new OrderWindowController();
		JSONObject obj = new JSONObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String email = "";
		String inputPwd = "";
		String test = null;

		try {
			// Get User Parameters
			email = request.getParameter("email");
			inputPwd = request.getParameter("password");
			test = request.getParameter("test"); // To know if it is for testing so that will print
													// in JSON instead

			/*
			 * (1) VERIFY USER CREDENTIALS. If user does not exist, returns null
			 */
			Employee emp = accessController.authenticateUser(email, inputPwd);
			if (emp == null) {
				// Throw Exception to return user back to login page
				throw new Exception("Authentication failed. Please check your credentials.");
			}
			System.out.println("User is authenticated: " + emp.getName());

			/*
			 * (2) CHECK USER STATUS. If user status is not ok, do something
			 */
			String empStatus = emp.getStatus();
			switch (empStatus) {
			case StringValues.EMPLOYEE_PENDING_VERIFICATION:
				throw new Exception(
						"It seems you have not verified your account. Please verify your account from your email first!");
			case StringValues.EMPLOYEE_SUSPENDED:
				session.setAttribute("suspended", "true");
				response.sendRedirect("/eureka_webservice/pages/payment.jsp");
				break;
			case StringValues.EMPLOYEE_DESTROYED:
				throw new Exception("Account has been disabled.");
			}

			/*
			 * (3) VERIFY AVAILABLE OPENED ORDER WINDOW.
			 */
			ArrayList<OrderWindow> windowList = orderWindowController
					.getAllOpenedWindowsForCompany(emp.getCompany());
			if (windowList == null || windowList.size() == 0) {
				System.out.println("windowList size: " + windowList.size());
			}

			/*
			 * ************* End of validation ********************
			 */

			OrderWindow window = null;
			String tokenID = UUID.randomUUID().toString().toUpperCase() + "|" + emp.getEmail()
					+ "|";

			if (!windowList.isEmpty()) {
				window = windowList.get(0);
			} else {
				session.setAttribute("user", emp);
				session.setAttribute("tokenID", tokenID);
				if (!response.isCommitted()) {
					response.sendRedirect("/eureka_webservice/pages/payment.jsp");
				}
			}

			// Setting user and token
			session.setAttribute("user", emp);
			session.setAttribute("tokenID", tokenID);
			session.setAttribute("orderWindow", window);
			System.out.println("TokenID is set in session");

			// for login2
			if (window != null) {
				ArrayList<Canteen> canteenList = new ArrayList<Canteen>();
				canteenList.add(window.getCanteen());
				session.setAttribute("canteenList", canteenList);
			}
			// For testing: print JSON rather than redirect
			if (test != null && test.equals("true")) {
				obj.put("user", emp.getEmail());
				obj.put("tokenID", tokenID);
				obj.put("status", "ok");
				out.print(gson.toJson(obj));
			} else {
				if (!response.isCommitted()) {
					response.sendRedirect("/eureka_webservice/pages/homepage.jsp");
				}
			}

		} catch (Exception e) {
			String errorMessage = e.getMessage();

			System.out.println("Exception@ProcessLoginServlet");
			System.out.println("Exception message: " + errorMessage);
			e.printStackTrace();

			session.setAttribute("email", email);

			if (errorMessage == null) {
				errorMessage = "Something went wrong! Please contact the administrator for help.";
			}

			// For testing: print JSON rather than redirect
			try {
				if (test != null && test.equals("true")) {
					obj.put("status", "error");
					obj.put("error", errorMessage);
					out.print(gson.toJson(obj));
				} else {
					session.setAttribute("error", errorMessage);
					if (!response.isCommitted()) {
						response.sendRedirect("/eureka_webservice/pages/login.jsp");
					}
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
	}
}

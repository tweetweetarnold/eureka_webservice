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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		AccessController accessController = new AccessController();
		CanteenController canteenController = new CanteenController();
		OrderWindowController orderWindowController = new OrderWindowController();
		JSONObject obj = new JSONObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String email = "";
		String inputPwd = "";
		String test = null;

		try {
			// Get User Parameters
			email = (String) request.getParameter("email");
			inputPwd = (String) request.getParameter("password");
			test = request.getParameter("test"); // For testing

			// (1) VERIFY USER CREDENTIALS. If user does not exist, returns
			// null.
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
				session.setAttribute("suspended", "true");
				response.sendRedirect("payment.jsp");
				break;
			case StringValues.EMPLOYEE_DESTROYED: 
				throw new Exception("Account has been disabled.");
			}

			// (3) VERIFY AVAILABLE OPENED ORDER WINDOW.
			ArrayList<OrderWindow> windowList = orderWindowController.getAllOpenedWindowsForCompany(emp.getCompany());
			if (windowList == null || windowList.size() == 0) {
				System.out.println("windowList size: " + windowList.size());
				if (!response.isCommitted()) {
					System.out.println("FGHJKL:DFGHJKL:DFGHJKLDFGHJKL:DFGHJKLDFGHJKLDFGHJKLDFGBHNJLDFGHJKL");
					session.setAttribute("suspended", "true");
					response.sendRedirect("orderHistory.jsp");
				}
			}
			// Setting user and token
			String tokenID = UUID.randomUUID().toString().toUpperCase() + "|" + emp.getEmail() + "|";
			session.setAttribute("user", emp);
			session.setAttribute("tokenID", tokenID);
			OrderWindow window = null;
			if (!windowList.isEmpty()) {
				window = windowList.get(0);

				session.setAttribute("orderWindow", window);
				System.out.println("TokenID is set in session");

				// for login2
				ArrayList<Canteen> canteenList = new ArrayList<Canteen>();
				canteenList.add(window.getCanteen());
				session.setAttribute("canteenList", canteenList);

				// For testing: print JSON rather than redirect
				if (test != null && test.equals("true")) {
					obj.put("user", emp.getEmail());
					obj.put("tokenID", tokenID);
					obj.put("status", "ok");
					out.print(gson.toJson(obj));
				} else {
					if (!response.isCommitted()) {
						response.sendRedirect("homepage.jsp");
					}
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
						response.sendRedirect("login.jsp");
					}
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

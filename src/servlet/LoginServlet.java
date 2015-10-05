package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.Food;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import services.PasswordService;
import controller.CanteenController;
import controller.LoginController;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject returnJson = new JSONObject();
		JSONObject jsonMessage = new JSONObject();

		try {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();

			// Getting User Input Parameters
			String username = (String) request.getParameter("username");
			String inputPwd = (String) request.getParameter("password");

			jsonMessage.put("username", username);

			// User Input Validation
			boolean isValid = (username != null && inputPwd != null && !username.equals("") && !inputPwd
					.equals(""));

			if (isValid) {
		
				LoginController loginController = new LoginController();
				Employee emp = loginController.authenticateUser(username,
						PasswordService.encryptPassword(inputPwd));

				if (emp != null) {
					// *** For Development only ***
					// creates a tokenID using UUID (Universalised Unique
					// Identifier
					// Object)
					// the user's username is tagged at the end of the token
					String tokenID = UUID.randomUUID().toString().toUpperCase() + "|"
							+ emp.getUsername() + "|";

					HttpSession session = request.getSession();
					session.setAttribute("user", emp);
					session.setAttribute("tokenID", tokenID);

					// Get all food data from database and save into session for
					// display
					CanteenController canteenController = new CanteenController();
					List<Food> allFoodList = canteenController.getAllFood();
					System.out.println("LoginServlet foodListSize: " + allFoodList.size());
					
					session.setAttribute("allFood", allFoodList);

//					JSONObject obj = new JSONObject();
//					obj.put("allFood", allFoodList);
//					System.out.println("OBJ: " + obj);
//					session.setAttribute("allFood", obj);

					response.sendRedirect("homepage.jsp");
				}

			} else {
				// Error Response

				throw new Exception(
						"Please ensure that you have inserted valid ID and password into the fields.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("message", jsonMessage);
			returnJson.put("error",
					"Please ensure that you have inserted valid ID and password into the fields.");
			returnJson.put("status", "fail");

			HttpSession session = request.getSession();
			session.setAttribute("error", returnJson);
			response.sendRedirect("login.jsp");
		}
	}
}

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

import model.Canteen;
import model.Employee;
import model.Food;
import services.PasswordService;
import controller.CanteenController;
import controller.LoginController;

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

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		// Getting User Input Parameters
		String username = (String) request.getParameter("username");
		String inputPwd = (String) request.getParameter("password");

		try {
			LoginController loginController = new LoginController();

			// Verify user credentials. if user does not exist, returns null
			Employee emp = loginController.authenticateUser(username,
					PasswordService.encryptPassword(inputPwd));
			System.out.println("User is authenticated: " + emp.getName());

			// *** For Development only ***
			// creates a tokenID using UUID (Universalised Unique Identifier
			// Object)
			// the user's username is tagged at the end of the token
			String tokenID = UUID.randomUUID().toString().toUpperCase() + "|" + emp.getUsername()
					+ "|";

			// Setting user and token
			session.setAttribute("user", emp);
			session.setAttribute("tokenID", tokenID);
			System.out.println("TokenID is set in session");

			// Get all food data from database and save into session for
			// display
			CanteenController canteenController = new CanteenController();
			List<Food> allFoodList = canteenController.getAllFood();
			System.out.println("LoginServlet foodListSize: " + allFoodList.size());
			
			//for login2
			List<Canteen> canteenList = canteenController.retrieveAll();
			System.out.println("canteen size: " + canteenList.size());
			session.setAttribute("canteenList", canteenList);

			session.setAttribute("allFood", allFoodList);
			System.out.println(allFoodList);

			response.sendRedirect("homepage.jsp");

		} catch (Exception e) {
			System.out.println("Exception thrown. Incorrect credentials.");
			session.setAttribute("username", username);
			session.setAttribute("error", "Something went wrong! Please check your credentials.");
			response.sendRedirect("login.jsp");
		}

	}
}

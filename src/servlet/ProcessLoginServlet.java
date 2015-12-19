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
import controller.AccessController;
import controller.CanteenController;

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

		HttpSession session = request.getSession();

		// Getting User Input Parameters
		String email = (String) request.getParameter("email");
		String inputPwd = (String) request.getParameter("password");

		try {
			AccessController accessController = new AccessController();
			
			// Verify user credentials. if user does not exist, returns null
			Employee emp = accessController.authenticateUser(email, inputPwd);
			System.out.println("emp: " + emp);
			System.out.println("User is authenticated: " + emp.getName());

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

			// Get all food data from database and save into session for
			// display
			CanteenController canteenController = new CanteenController();
			// List<Food> allFoodList = canteenController.getAllFood();
			// System.out.println("LoginServlet foodListSize: " +
			// allFoodList.size());

			// for login2
			List<Canteen> canteenList = canteenController.getAllCanteens();
			System.out.println("canteen size: " + canteenList.size());
			session.setAttribute("canteenList", canteenList);
			//
			// session.setAttribute("allFood", allFoodList);
			// System.out.println(allFoodList);

			response.sendRedirect("homepage.jsp");

		} catch (Exception e) {
			System.out.println("Exception thrown. Incorrect credentials.");
			System.out.println("Exception message: " + e.getMessage());
			e.printStackTrace();
			session.setAttribute("email", email);
			// problem test here
			// String ciphertext = PasswordService.encryptPassword(inputPwd);
			// String decryptedLoginInput =
			// PasswordService.decryptPassword(ciphertext);
			// session.setAttribute("loginCipherText",ciphertext);
			// UserController userController = new UserController();
			// Employee tempe =
			// userController.retrieveEmployeeViaUsername(username);
			// String actualCiphertext = tempe.getPassword();
			// String decryptedPassword =
			// PasswordService.decryptPassword(actualCiphertext);

			session.setAttribute("error", "Something went wrong! Please check your credentials.");
			// session.setAttribute("error",
			// "Something went wrong! Please check your credentials. password input:<"
			// + ciphertext + ">Password needed:<"+
			// actualCiphertext+">"+"Decrypted input:<"+decryptedLoginInput+"> User Actual Decrypted:<"+
			// decryptedPassword+">");
			response.sendRedirect("login.jsp");
		}

	}
}

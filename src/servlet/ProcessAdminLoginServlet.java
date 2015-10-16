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

import controller.AdminController;
import controller.CanteenController;
import controller.LoginController;
import model.Admin;
import model.Employee;
import model.Food;
import services.PasswordService;

/**
 * Servlet implementation class ProcessAdminLoginServlet
 */
@WebServlet("/ProcessAdminLoginServlet")
public class ProcessAdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessAdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		// Getting User Input Parameters
		String adminUsername = (String) request.getParameter("adminUsername");
		String adminInputPwd = (String) request.getParameter("adminPassword");

		try {
			LoginController loginController = new LoginController();

			// Verify user credentials. if user does not exist, returns null
			Admin admin = loginController.authenticateAdmin(adminUsername,
					PasswordService.encryptPassword(adminInputPwd));
			System.out.println("Admin is authenticated: " + admin.getName());

			// *** For Development only ***
			// creates a tokenID using UUID (Universalised Unique Identifier
			// Object)
			// the user's username is tagged at the end of the token
			String tokenID = UUID.randomUUID().toString().toUpperCase() + "|" + admin.getUsername()
					+ "|";

			// Setting user and token
			session.setAttribute("admin", admin);
			session.setAttribute("tokenID", tokenID);
			System.out.println("TokenID is set in session");
			AdminController adminController = new AdminController();
			session.setAttribute("outstandingPayments", adminController.getListOfOwedPayment("Owe"));
//			response.sendRedirect("adminHomepage.jsp");
			response.sendRedirect("adminHomepageTest.jsp");

		} catch (Exception e) {
			System.out.println("Exception thrown. Incorrect credentials.");
			session.setAttribute("username", adminUsername);
			session.setAttribute("error", "Something went wrong! Please check your credentials.");
			response.sendRedirect("adminLogin.jsp");
		}

	}
}

package servlet.process.admin;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import services.PasswordService;
import controller.AccessController;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccessController loginController = new AccessController();

		// Getting User Input Parameters
		String adminUsername = (String) request.getParameter("adminUsername");
		String adminInputPwd = (String) request.getParameter("adminPassword");

		try {
			// Verify user credentials. if user does not exist, returns null
			Admin admin = loginController.authenticateAdmin(adminUsername,
					PasswordService.encryptPassword(adminInputPwd));
			System.out.println("Admin is authenticated: " + admin.getName());

			String tokenID = UUID.randomUUID().toString().toUpperCase() + "|" + admin.getUsername()
					+ "|";

			// Setting user and token
			session.setAttribute("admin", admin);
			session.setAttribute("tokenID", tokenID);

			System.out.println("TokenID is set in session");

			response.sendRedirect("/eureka_webservice/LoadOrderWindowOpenedServlet");

		} catch (Exception e) {
			System.out.println("Exception thrown. Incorrect credentials.");
			session.setAttribute("username", adminUsername);
			session.setAttribute("error", "Something went wrong! Please check your credentials.");
			response.sendRedirect("/eureka_webservice/admin/login.jsp");
		}
	}
}

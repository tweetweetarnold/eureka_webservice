package servlet.process.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import services.AESAlgorithm;
import controller.AccessController;
import controller.EmployeeController;

/**
 * Servlet implementation class ProcessResetPasswordServlet
 */
@WebServlet("/ProcessResetPasswordRedirectServlet")
public class ProcessResetPasswordRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessResetPasswordRedirectServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("****** ProcessResetPasswordRedirectServlet ******");
		HttpSession session = request.getSession();
		response.setContentType("application/json");

		AESAlgorithm aesAlgo = new AESAlgorithm();
		EmployeeController userController = new EmployeeController();
		AccessController accessController = new AccessController();

		String email = (String) session.getAttribute("email");
		String newPassword = request.getParameter("newPassword");
		String newPasswordConfirmation = request.getParameter("confirmPassword");

		try {
			Employee employee = userController.retrieveEmployeeViaEmail(email);
			ArrayList<String> errorMessages = accessController.checkPasswordMeetRequirements(
					newPassword, newPasswordConfirmation);

			if (!errorMessages.isEmpty()) {
				String msg = "";
				for (String s : errorMessages) {
					msg = s + "\n" + msg;
				}
				throw new Exception(msg);

			} else {
				accessController.updateEmployeePassword(employee, newPassword);
				session.setAttribute("success", "Password has been updated!");
				response.sendRedirect("/eureka_webservice/pages/login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/pages/set-new-password.jsp");
		}
	}
}

package servlet.process.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import controller.AccessController;
import controller.EmployeeController;

/**
 * Servlet implementation class ProcessResetPasswordServlet
 */
@WebServlet("/ProcessResetPasswordServlet")
public class ProcessResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessResetPasswordServlet() {
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

		HttpSession session = request.getSession();

		EmployeeController employeeController = new EmployeeController();
		AccessController accessController = new AccessController();

		String emailAddress = (String) request.getParameter("email");
		String[] toSendEmail = { emailAddress };

		try {
			session.setAttribute("email", emailAddress);

			Employee employee = employeeController.getEmployeeByEmail(emailAddress);
			if (employee == null) {
				throw new Exception("You have not registered an account");
			}

			accessController.constructResetPasswordEmail(request.getServerName(),
					request.getServerPort(), request.getContextPath(), emailAddress, toSendEmail);

			session.setAttribute("success", "An email has been sent to you. "
					+ "Please check your email within 5 minutes for instructions on resetting your password");
			response.sendRedirect("/eureka_webservice/pages/login.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/pages/reset-password.jsp");
		}
	}
}

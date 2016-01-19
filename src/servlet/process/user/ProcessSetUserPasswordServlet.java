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
import controller.AccessController;

/**
 * Servlet implementation class ProcessSetUserPasswordServlet
 */
@WebServlet("/ProcessSetUserPasswordServlet")
public class ProcessSetUserPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessSetUserPasswordServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		AccessController accessController = new AccessController();

		Employee e = (Employee) session.getAttribute("user");

		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmNewPassword = request.getParameter("confirmNewPassword");

		try {
			
			boolean ans = accessController.updateEmployeePassword(e, oldPassword, newPassword,
					confirmNewPassword);
			if (ans) {
				session.setAttribute("success", "Password updated successfully.");
				response.sendRedirect("/eureka_webservice/pages/profile.jsp");
			} 

		} catch (Exception e1) {
			e1.printStackTrace();
			session.setAttribute("error", e1.getMessage());
			response.sendRedirect("/eureka_webservice/pages/profile.jsp");
		}
	}
}

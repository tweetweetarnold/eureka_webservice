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
import controller.EmployeeController;

/**
 * Servlet implementation class ProcessSetUserContactNumberServlet
 */
@WebServlet("/ProcessSetUserContactNumberServlet")
public class ProcessSetUserContactNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessSetUserContactNumberServlet() {
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
		EmployeeController employeeController = new EmployeeController();
		AccessController accessController = new AccessController();

		Employee emp = (Employee) session.getAttribute("user");
		String contactNoString = request.getParameter("contactNo");

		try {
			ArrayList<String> errorMessages = accessController
					.checkContactNoRequirements(contactNoString);
			if (!errorMessages.isEmpty()) {
				throw new Exception(errorMessages.get(0));
			}

			emp.setContactNo(Long.parseLong(contactNoString));
			employeeController.updateEmployee(emp);

			session.setAttribute("success", "Contact number saved successfully.");
			response.sendRedirect("/eureka_webservice/pages/profile.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/pages/profile.jsp");
		}

	}
}

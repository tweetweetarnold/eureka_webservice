package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import controller.EmployeeController;

/**
 * Servlet implementation class ProcessSuspendUserServlet
 */
@WebServlet("/ProcessSuspendUserServlet")
public class ProcessSuspendUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessSuspendUserServlet() {
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

	// Suspend employee with email
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("****** ProcessSuspendUserServlet ******");
		HttpSession session = request.getSession();

		EmployeeController userController = new EmployeeController();

		try {
			String userInput = request.getParameter("email");

			Employee employee = userController.retrieveEmployeeViaEmail(userInput);
			employee.setStatus("Suspended");
			userController.updateEmployee(employee);
			System.out.println(userInput + " suspended.");

			session.setAttribute("success", userInput + "has been suspended.");

			response.sendRedirect("adminHomepage.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			session.setAttribute("error", "Oops! Something went wrong! Please check your inputs.");
			response.sendRedirect("PLEASECHANGEME.jsp");
		}
	}
}

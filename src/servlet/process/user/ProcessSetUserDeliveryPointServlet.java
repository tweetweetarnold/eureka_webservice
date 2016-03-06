package servlet.process.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.EmployeeController;
import model.Employee;

/**
 * Servlet implementation class ProcessSetUserDeliveryPointServlet
 */
@WebServlet("/ProcessSetUserDeliveryPointServlet")
public class ProcessSetUserDeliveryPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessSetUserDeliveryPointServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		EmployeeController employeeCtrl = new EmployeeController();

		try {
			Employee emp = (Employee) session.getAttribute("user");
			String deliveryPoint = request.getParameter("deliveryPoint");

			emp.setDeliveryPoint(deliveryPoint);

			employeeCtrl.updateEmployee(emp);

			session.setAttribute("success", "Delivery Point updated successfully");
			response.sendRedirect("/eureka_webservice/pages/profile.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/pages/profile.jsp");
		}

	}

}

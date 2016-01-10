package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import controller.FinanceController;

/**
 * Servlet implementation class LoadAdminViewUsersWithOutstandingPaymentServlet
 */
@WebServlet("/LoadAdminViewUsersWithOutstandingPaymentServlet")
public class LoadAdminViewUsersWithOutstandingPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewUsersWithOutstandingPaymentServlet() {
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
		FinanceController financeController = new FinanceController();

		ArrayList<Employee> list = financeController.getAllUsersWithOutstandingPayment(0, false);
		session.setAttribute("outstandingList", list);

		response.sendRedirect("/eureka_webservice/admin/outstanding-payment.jsp");
	}

}

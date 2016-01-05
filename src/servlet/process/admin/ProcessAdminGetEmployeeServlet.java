package servlet.process.admin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.FoodOrder;
import controller.EmployeeController;
import controller.FoodOrderController;

/**
 * Servlet implementation class processGetEmployeeServlet
 */
@WebServlet("/ProcessAdminGetEmployeeServlet")
public class ProcessAdminGetEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminGetEmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String email = request.getParameter("email");

		FoodOrderController foodOrderController = new FoodOrderController();
		List<FoodOrder> foodOrderList = foodOrderController.getFoodOrderSet(email);
		EmployeeController userController = new EmployeeController();

		Employee employee = userController.retrieveEmployeeViaEmail(email);

		session.setAttribute("name", employee.getName());
		session.setAttribute("email", employee.getEmail());
		session.setAttribute("contactNumber", employee.getContactNo());
		session.setAttribute("status", employee.getStatus());
		session.setAttribute("orderHistory", foodOrderList);
		DecimalFormat df = new DecimalFormat("####0.00");
		session.setAttribute("amountOwed", df.format(employee.getAmountOwed()));

		response.sendRedirect("adminProfile.jsp");
	}

}

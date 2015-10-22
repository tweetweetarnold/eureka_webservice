package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.FoodOrder;
import controller.FoodOrderController;

/**
 * Servlet implementation class GetEmployeeOrderHistoryServlet
 */
@WebServlet("/ProcessProfileServlet")
public class ProcessProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		// PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("user");
		System.out.println(emp.getUsername());

		FoodOrderController foodOrderController = new FoodOrderController();
		List<FoodOrder> foodOrderList = foodOrderController.getFoodOrderSet(emp.getUsername());
		System.out.println("sizehere: " + foodOrderList.size());

		session.setAttribute("orderHistory", foodOrderList);
		response.sendRedirect("profile.jsp");

		// RequestDispatcher rd =
		// request.getRequestDispatcher("retrieveEmployeeOrderHistory.jsp");
		// request.setAttribute("foodOrderList", foodOrderList);
		// rd.forward(request, response);
	}

}

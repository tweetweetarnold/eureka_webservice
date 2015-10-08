package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.FoodOrder;
import model.FoodOrderItem;
import controller.FoodOrderController;

/**
 * Servlet implementation class AddNewFoodOrder
 */
@WebServlet("/AddNewFoodOrder")
public class AddNewFoodOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewFoodOrderServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Retrieve myFoodOrders and User
		Set<FoodOrderItem> myFoodOrderItems = (HashSet<FoodOrderItem>) session
				.getAttribute("myFoodOrderItems");
		System.out.println("MyFoodOrderItems retrieved");
		Employee employee = (Employee) session.getAttribute("user");
		System.out.println("Employee retrieved");

		FoodOrder foodOrder = new FoodOrder("Confirmed", employee, null, myFoodOrderItems,
				new Date());
		System.out.println("New FoodOrder created");

		// Process new FoodOrder
		FoodOrderController controller = new FoodOrderController();
		controller.addFoodOrder(foodOrder);
		System.out.println("New FoodOrder added to database");

		response.sendRedirect("cart.jsp");
	}

}

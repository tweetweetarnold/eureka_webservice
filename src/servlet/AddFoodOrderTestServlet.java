package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.FoodOrderController;
import model.Employee;
import model.FoodOrder;
import model.FoodOrderItem;
import value.StringValues;

/**
 * Servlet implementation class AddFoodOrderTestServlet
 */
@WebServlet("/AddFoodOrderTestServlet")
public class AddFoodOrderTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFoodOrderTestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// Added somecode to increase the quantity of the FoodOrderItems-Chris
		ArrayList<FoodOrderItem> uniqueFoodOrderItems = new ArrayList<FoodOrderItem>();
		// Retrieve myFoodOrders and User

		List<FoodOrderItem> myFoodOrderItems = (List<FoodOrderItem>) session
				.getAttribute("myFoodOrderItems");
		System.out.println("MyFoodOrderItems retrieved");
		Set<FoodOrderItem> hashMyFoodOrderItems = new HashSet<>(myFoodOrderItems);

		Employee employee = (Employee) session.getAttribute("user");
		System.out.println("Employee retrieved");
		HashSet<FoodOrderItem> foodOrderItemsToAdd = new HashSet<FoodOrderItem>();
		// FoodOrder myFoodOrder = new FoodOrder(StringValues.ORDER_CONFIRMED, employee, null);
		FoodOrderController foodOrderController = new FoodOrderController();
		// foodOrderController.addFoodOrder(myFoodOrder);

	}

}

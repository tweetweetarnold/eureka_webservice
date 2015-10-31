package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import value.StringValues;
import controller.FoodOrderController;

/**
 * Servlet implementation class AddNewFoodOrder
 */
@WebServlet("/AddFoodOrderServlet")
public class AddFoodOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFoodOrderServlet() {
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

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		System.out.println("****** AddNewFoodOrderServlet ******");

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
		FoodOrder myFoodOrder = new FoodOrder(StringValues.ORDER_CONFIRMED, employee, null);
		for (FoodOrderItem item : myFoodOrderItems) {
			// Loop for uniqueFoodOrderItems
			int count = 0;
			for (FoodOrderItem uniqueItem : myFoodOrderItems) {
				if (uniqueItem.equals(item)) {
					count++;
				}
			}
			System.out.println("NUMBER OF DUPLICATES " + count);
			item.setQuantity(count);
			Iterator iter = foodOrderItemsToAdd.iterator();
			boolean duplicatesExist = false;
			while (iter.hasNext()) {
				FoodOrderItem tempFoodOrderItem = (FoodOrderItem) iter.next();
				if (item.equals(tempFoodOrderItem)) {
					duplicatesExist = true;
				}
			}
			if (!duplicatesExist) {
				foodOrderItemsToAdd.add(item);
			}
			// item.setFoodOrder(myFoodOrder);
			System.out.println("WHY : " + item.toString());
			out.println("size: " + item.getModifierChosenList().size());
		}

		Iterator iter = foodOrderItemsToAdd.iterator();
		while (iter.hasNext()) {
			FoodOrderItem foodItem = (FoodOrderItem) iter.next();
			foodItem.setFoodOrder(myFoodOrder);
		}

		HashSet tempHashSet = new HashSet(uniqueFoodOrderItems);
		System.out.println("99999999999999999999999999999999           "
				+ foodOrderItemsToAdd.size());
		myFoodOrder.setFoodOrderList(foodOrderItemsToAdd);
		System.out.println("New FoodOrder created");

		employee.setAmountOwed(employee.getAmountOwed() + myFoodOrder.getTotalPrice());
		System.out.println("Employee amount owed updated");

		// Process new FoodOrder
		FoodOrderController controller = new FoodOrderController();
		controller.addFoodOrder(myFoodOrder);
		System.out.println("New FoodOrder added to database");

		session.removeAttribute("myFoodOrderItems");
		System.out.println("myFoodOrderItems cleared");

		session.setAttribute("success", "Yay! Your order has been submitted!");
		response.sendRedirect("cart.jsp");
	}
}

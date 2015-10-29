package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

		List<FoodOrderItem> myFoodOrderItems = (List<FoodOrderItem>) session.getAttribute("myFoodOrderItems");
		System.out.println("MyFoodOrderItems retrieved");
		Set<FoodOrderItem> hashMyFoodOrderItems = new HashSet<>(myFoodOrderItems);

		Employee employee = (Employee) session.getAttribute("user");
		System.out.println("Employee retrieved");

		FoodOrder myFoodOrder = new FoodOrder(StringValues.ORDER_CONFIRMED, employee, null);
		for (FoodOrderItem item : myFoodOrderItems) {
			// Loop for uniqueFoodOrderItems
			if (!uniqueFoodOrderItems.isEmpty()) {
				for (FoodOrderItem uniqueItem : uniqueFoodOrderItems) {
					if(uniqueItem.equals(item)){
						uniqueItem.setQuantity(item.getQuantity()+1);
					}
				}
				
			}else{
				uniqueFoodOrderItems.add(item);
			}
			//item.setFoodOrder(myFoodOrder);

			out.println("size: " + item.getModifierChosenList().size());
		}
		HashSet tempHashSet = new HashSet(uniqueFoodOrderItems);
		myFoodOrder.setFoodOrderList(tempHashSet);
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
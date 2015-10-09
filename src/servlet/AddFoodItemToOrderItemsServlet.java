package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Food;
import model.FoodOrderItem;

/**
 * Servlet implementation class AddFoodItemToSessionServlet
 */
@WebServlet(description = "This servlet adds a food item to be stored in session", urlPatterns = { "/AddFoodItemToSessionServlet" })
public class AddFoodItemToOrderItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFoodItemToOrderItemsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		System.out.println("AddFoodItemToSessionServlet");

		try {
			HttpSession session = request.getSession(); // Get Session

			// Retrieve foodId from parameters
			// ** might need to change in future because its from foodlist array
			String foodId = request.getParameter("foodId");
			System.out.println("foodId: " + foodId);

			// Retrieve corresponding food from food list
			// ** Temporary
			List<Food> allFood = (ArrayList<Food>) session.getAttribute("allFood");
			Food food = allFood.get(Integer.parseInt(request.getParameter("foodId")));
			System.out.println("allFood: " + allFood); // testing
			System.out.println("chosenFood: " + food); // testing

			// Retrieve user's current food orders
			List<FoodOrderItem> myFoodOrderItems = (ArrayList<FoodOrderItem>) session
					.getAttribute("myFoodOrderItems");
			System.out.println("myFoodOrderItems: " + myFoodOrderItems);
			if (myFoodOrderItems == null) {
				myFoodOrderItems = new ArrayList<FoodOrderItem>();
				System.out.println("myFoodOrderItems is created");
			}

			// Create new FoodOrderItem
			FoodOrderItem foodItem = new FoodOrderItem(null, food, 1, food.getPrice(), "",
					new Date());
			System.out.println("new FoodOrderItem created");

			// Add new FoodOrderItem to myFoodOrders
			myFoodOrderItems.add(foodItem);

			// Set myFoodOrders in Session
			session.setAttribute("myFoodOrderItems", myFoodOrderItems);

			// Redirect back to homepage
			System.out.println("new FoodOrderItem added to myFoodOrderItems successfully");
			response.sendRedirect("homepage.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

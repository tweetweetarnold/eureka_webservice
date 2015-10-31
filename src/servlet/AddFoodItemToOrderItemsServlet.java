package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Food;
import model.FoodOrderItem;
import model.Modifier;
import model.ModifierChosen;
import dao.FoodDAO;

/**
 * Servlet implementation class AddFoodItemToSessionServlet
 */
@WebServlet(description = "This servlet adds a food item to be stored in session", urlPatterns = { "/AddFoodItemToOrderItemsServlet" })
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
		PrintWriter out = response.getWriter();
		
		System.out.println("");
		System.out.println("****** AddFoodItemToOrderItemsServlet ******");
		out.println("AddFoodItemToOrderItemsServlet");

		try {
			HttpSession session = request.getSession(); // Get Session

			// Retrieve foodId from parameters
			// ** might need to change in future because its from foodlist array
			String foodId = request.getParameter("foodId");
			System.out.println("foodId: " + foodId);

			// Retrieve corresponding food from food list
			// ** Temporary
//			List<Food> allFood = (ArrayList<Food>) session.getAttribute("allFood");
//			Food food = allFood.get(Integer.parseInt(request.getParameter("foodPos")));
//			System.out.println("allFood: " + allFood); // testing
//			System.out.println("chosenFood: " + food.getName() + ", id: " + food.getFoodId()); // testing
			
			Food food = FoodDAO.getFood(Integer.parseInt(foodId));
			System.out.println("food retrieved: " + food.getName());

			// Retrieve user's current food orders
			List<FoodOrderItem> myFoodOrderItems = (ArrayList<FoodOrderItem>) session
					.getAttribute("myFoodOrderItems");
			System.out.println("myFoodOrderItems: " + myFoodOrderItems);
			if (myFoodOrderItems == null) {
				myFoodOrderItems = new ArrayList<FoodOrderItem>();
				System.out.println("myFoodOrderItems is created");
			}

			// Create new FoodOrderItem
			FoodOrderItem foodItem = new FoodOrderItem(null, food, 1, "");
			System.out.println("new FoodOrderItem created");

			// Add new FoodOrderItem to myFoodOrders
			myFoodOrderItems.add(foodItem);

			// Get Modifiers
			Set<Modifier> modifierList = food.getModifierList();
			for (Modifier m : modifierList) {
				String name = m.getName();
				String value = request.getParameter(name);
				if (value != null) {
					Set<ModifierChosen> list = foodItem.getModifierChosenList();
					out.println("<br>List: " + list);
					ModifierChosen mc = new ModifierChosen(m, foodItem);
					out.println(mc);
					list.add(mc);
					foodItem.setModifierChosenList(list);
					out.println("fooditem: " + foodItem.getModifierChosenList());
				}
				System.out.println("name: " + name + ", value: " + value);
			}

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

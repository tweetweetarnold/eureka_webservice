package servlet.process.user;

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
@WebServlet(description = "This servlet adds a food item to be stored in session", urlPatterns = { "/ProcessAddFoodItemToOrderItemsServlet" })
public class ProcessAddFoodItemToOrderItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FoodDAO foodDAO = new FoodDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAddFoodItemToOrderItemsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		System.out.println("****** ProcessAddFoodItemToOrderItemsServlet ******");
		out.println("ProcessAddFoodItemToOrderItemsServlet");

		try {
			HttpSession session = request.getSession(); // Get Session

			// Retrieve foodId from parameters
			// ** might need to change in future because its from foodlist array
			String foodId = request.getParameter("foodId");
			System.out.println("foodId: " + foodId);

			Food food = foodDAO.getFood(Integer.parseInt(foodId));
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
				System.out.println("Chris Test-----> Expected " + name + " retrieved: " + request.getParameter("modifierDropdown"));
				String value = request.getParameter(name);
				String dropdownValue = request.getParameter("modifierDropdown");
				if (value != null) {
					Set<ModifierChosen> list = foodItem.getModifierChosenList();
					out.println("<br>List: " + list);
					ModifierChosen mc = new ModifierChosen(m, foodItem);
					out.println(mc);
					list.add(mc);
					foodItem.setModifierChosenList(list);
					out.println("fooditem: " + foodItem.getModifierChosenList());
				}
				if (dropdownValue != null && dropdownValue.equals(m.getName())) {
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
			session.setAttribute("success", food.getName() + " has been added to cart!");

			response.sendRedirect("/eureka_webservice/pages/stall-foods.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	}
}
